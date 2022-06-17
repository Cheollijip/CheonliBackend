package com.example.springbackend.configuration.security

import com.example.springbackend.user.infrastructure.spi.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.bson.types.ObjectId
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomAuthenticationManager(
    private val userRepository: UserRepository
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> = mono {
        val userOrNull = userRepository.findById(ObjectId(authentication.name))
        val user = userOrNull.awaitSingle()
        UsernamePasswordAuthenticationToken(user.id, user.id)
    }
}