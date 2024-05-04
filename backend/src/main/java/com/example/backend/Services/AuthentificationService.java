package com.example.backend.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.RefreshToken;
import com.example.backend.Entity.Role;
import com.example.backend.Entity.User;
import com.example.backend.EntityRepository.RoleRepository;
import com.example.backend.EntityRepository.UserRepository;
import com.example.backend.Enum.ERole;
import com.example.backend.Payload.Request.LoginRequest;
import com.example.backend.Payload.Request.SignUpRequest;
import com.example.backend.Payload.Response.JwtResponse;
import com.example.backend.Payload.Response.MessageResponse;
import com.example.backend.Security.Jwt.JwtUtils;
import com.example.backend.Security.Services.RefreshTokenService;
import com.example.backend.Security.Services.UserDetailsImplementation;


@Service
public class AuthentificationService {
    @Autowired
    private AuthenticationManager authentificationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public MessageResponse register(SignUpRequest signUpRequest) {
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getPassword());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                switch (role) {
                    case "admin":
                        roles.add(adminRole);
                        roles.add(modRole);
                        roles.add(userRole);
                        break;
                    case "mod":
                        roles.add(modRole);
                        roles.add(userRole);
                        break;
                    default:
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponse("User Registred Successfully");
    }

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authentificationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
                        userDetails.getEmail(), roles);
    }


}
