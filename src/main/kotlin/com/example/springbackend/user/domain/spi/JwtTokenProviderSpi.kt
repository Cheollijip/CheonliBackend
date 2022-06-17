package com.example.springbackend.user.domain.spi

interface JwtTokenProviderSpi {
    fun generateToken(subject: String): String
}
