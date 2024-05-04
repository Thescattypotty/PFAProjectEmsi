package com.example.backend.EntityListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.backend.Entity.BaseEntity;
import com.example.backend.Entity.User;
import com.example.backend.EntityRepository.UserRepository;
import com.example.backend.Exception.UserNotSignedInException;
import com.example.backend.Security.Services.UserDetailsImplementation;

import java.util.Date;
import java.util.Optional;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;

@EntityListeners(CreatedByListener.class)
public class CreatedByListener {
    /* */
    @Autowired
    private UserRepository userRepository;

    @PrePersist
    public void BeforePersist(Object entity)
    {
        if(entity instanceof BaseEntity)
        {
            BaseEntity baseEntity = (BaseEntity) entity;
            
            UserDetailsImplementation userDetails = (UserDetailsImplementation) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            Optional<User> userFound = userRepository.findByUsername(userDetails.getUsername());
            if (userFound.isPresent()) {
                User user = userFound.get();
                baseEntity.setCreatedBy(user);
                baseEntity.setCreatedAt(new Date());
            }
            else {
                throw new UserNotSignedInException("The user is not logged in so he can't do anything !");
            }
        }
    }
}
