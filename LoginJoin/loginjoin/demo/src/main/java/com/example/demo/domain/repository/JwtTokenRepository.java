package com.example.demo.domain.repository;

import com.example.demo.domain.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<JwtToken,Long> {
}
