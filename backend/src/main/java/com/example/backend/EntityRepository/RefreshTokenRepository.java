package com.example.backend.EntityRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.RefreshToken;
import com.example.backend.Entity.User;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken , Long>
{

    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
    Optional<RefreshToken> findByUserId(Long userId);
}
