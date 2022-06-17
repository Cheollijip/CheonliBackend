package com.example.springbackend.configuration.security

import com.example.springbackend.configuration.security.jwt.properties.JwtTokenExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers

@Configuration
class CustomAuthenticationWebFilter(
    private val authenticationManager: ReactiveAuthenticationManager,
    private val jwtTokenExtractor: JwtTokenExtractor,
) {
    @Bean
    fun authenticationWebFilter(): AuthenticationWebFilter {
        return AuthenticationWebFilter(authenticationManager).apply {
            setServerAuthenticationConverter(jwtTokenExtractor)
            setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.anyExchange())
        }
    }
}
