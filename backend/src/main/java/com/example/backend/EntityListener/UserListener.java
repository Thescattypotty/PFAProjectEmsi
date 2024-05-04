package com.example.backend.EntityListener;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.backend.Entity.Role;
import com.example.backend.Entity.User;
import com.example.backend.EntityRepository.RoleRepository;
import com.example.backend.EntityRepository.UserRepository;
import com.example.backend.Enum.ERole;
import com.example.backend.Exception.ResourceNotFoundException;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;

@EntityListeners(UserListener.class)
public class UserListener {
    /* */
    
    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PrePersist
    public void initializeUserPassword(User user)
    {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent())
        {
            User CurrentUser = userOptional.get();
            if(verifyPassword(user.getPassword(), CurrentUser.getPassword()))
            {
                user.setPassword(encoder.encode(user.getPassword()));
            }
        }
        else{
            user.setPassword(encoder.encode(user.getPassword()));
        }
        Set<Role> roles = user.getRoles();
        Role role_admin = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        Role role_moderator = roleRepository.findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        Role role_user = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));

        if (!roles.contains(role_admin)) {
            if (roles.contains(role_moderator)) {
                roles.add(role_user);
            } else if (!roles.contains(role_user)) {
                roles.add(role_moderator);
                roles.add(role_user);
            }
        }
    }
    
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

}
