package com.example.backend.Security.Authorization;

import java.util.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.backend.Annotation.RequiresPermission;
import com.example.backend.Entity.User;
import com.example.backend.EntityRepository.UserRepository;
import com.example.backend.Enum.EPermission;
import com.example.backend.Exception.AccessDeniedException;
import com.example.backend.Security.Services.UserDetailsImplementation;

@Aspect
@Component
public class PermissionAuthorizationAspect {
    @Autowired
    private UserRepository userRepository;

    @Before("@annotation(requiresPermission)")
    public void checkPermission(JoinPoint joinPoint , RequiresPermission requiresPermission)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !hasRequiredPermission(authentication , requiresPermission.value()))
        {
            throw new AccessDeniedException("Access denied");
        }
    }
    private boolean hasRequiredPermission(Authentication authentication , String[] requiredPermissions)
    {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        if(user.isPresent())
        {
            User userFound = user.get();
            for (String permission : requiredPermissions) {
                if (
                    userFound.getPermissions().stream()
                        .anyMatch(perm -> perm.getName().equals(EPermission.valueOf(permission)))
                        ||
                    userFound.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                        .anyMatch(perm -> perm.getName().equals(EPermission.valueOf(permission)))
                )
                    return true;
            }
        }
        return false;
    }
}
