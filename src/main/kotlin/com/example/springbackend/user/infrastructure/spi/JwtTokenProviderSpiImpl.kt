package com.example.springbackend.user.infrastructure.spi

import com.example.springbackend.jwt.properties.JwtProperties
import com.example.springbackend.user.domain.spi.JwtTokenProviderSpi
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import org.springframework.stereotype.Repository

@Repository
class JwtTokenProviderSpiImpl(
    private val jwtProperties: JwtProperties
) : JwtTokenProviderSpi {
    override fun generateToken(subject: String): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey.toByteArray())
            .setIssuedAt(Date())
            .setSubject(subject)
            .claim("test", "test")
            .compact()
    }
}