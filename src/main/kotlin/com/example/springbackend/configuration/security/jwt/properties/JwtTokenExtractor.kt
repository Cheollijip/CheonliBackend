package com.example.springbackend.configuration.security.jwt.properties

import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtTokenExtractor(
    private val tokenParser: TokenParser
) : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange): Mono<Authentication> = mono {
        val authorization = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
        authorization?.let {
            val pureToken = authorization.split(" ")[1]
            val claims = tokenParser.parseToken(pureToken)

            val user = User(
                claims.subject,
                "sdafs",
                listOf()
            )

            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(user, "", user.authorities)
            usernamePasswordAuthenticationToken
        }
    }
}