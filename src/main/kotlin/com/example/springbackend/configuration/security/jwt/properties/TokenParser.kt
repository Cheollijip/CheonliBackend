package com.example.springbackend.configuration.security.jwt.properties

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component

@Component
class TokenParser(
    private val jwtProperties: JwtProperties
) {
    fun parseToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(jwtProperties.secretKey.toByteArray())
            .parseClaimsJws(token).body
    }
}