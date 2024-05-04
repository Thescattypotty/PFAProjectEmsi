package com.example.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.EntityRepository.UserRepository;
import com.example.backend.Payload.Response.MessageResponse;
import com.example.backend.Security.Services.RefreshTokenService;

@Service
public class BadRequetService {
 
    @Autowired
    UserRepository userRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    public MessageResponse checkIfUsernameExist(String username)
    {
        if (userRepository.existsByUsername(username)) {

            return new MessageResponse("Error:"+ username + "is already taken!");
        }
        return null;
    }
    
    public MessageResponse checkIfEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {

            return new MessageResponse("Error:"+ email +"is already taken!");
        }
        return null;
    }
}
