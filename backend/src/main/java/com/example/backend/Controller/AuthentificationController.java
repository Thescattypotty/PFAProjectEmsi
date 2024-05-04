package com.example.backend.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.backend.Entity.RefreshToken;
import com.example.backend.Exception.TokenRefreshException;
import com.example.backend.Payload.Request.LoginRequest;
import com.example.backend.Payload.Request.SignUpRequest;
import com.example.backend.Payload.Request.TokenRefreshRequest;
import com.example.backend.Payload.Response.JwtResponse;
import com.example.backend.Payload.Response.MessageResponse;
import com.example.backend.Payload.Response.TokenRefreshResponse;
import com.example.backend.Security.Jwt.JwtUtils;
import com.example.backend.Security.Services.RefreshTokenService;
import com.example.backend.Security.Services.UserDetailsImplementation;
import com.example.backend.Services.AuthentificationService;
import com.example.backend.Services.BadRequetService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
    @Autowired
    private BadRequetService badRequestService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private AuthentificationService authentificationService;
    
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authentificationService.login(loginRequest));
    }

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(badRequestService.checkIfEmailExist(signUpRequest.getEmail()) != null)
        {
            return ResponseEntity.badRequest().body(
                    badRequestService.checkIfEmailExist(signUpRequest.getEmail()));
        }
        if(badRequestService.checkIfUsernameExist(signUpRequest.getUsername()) != null)
        {
            return ResponseEntity.badRequest().body(
                    badRequestService.checkIfUsernameExist(signUpRequest.getUsername()));
        }

        return ResponseEntity.ok(authentificationService.register(signUpRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
    @GetMapping("/profile")
    public ResponseEntity<?> GetUser() {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
                        userDetails.getEmail(), roles));
        
    }

}
