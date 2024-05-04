package com.example.backend.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entity.User;
import com.example.backend.EntityRepository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImplementation loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImplementation.build(user);
    }

}
