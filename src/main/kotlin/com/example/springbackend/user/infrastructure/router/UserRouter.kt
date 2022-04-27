package com.example.springbackend.user.infrastructure.router

import com.example.springbackend.user.infrastructure.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class UserRouter(
    private val userHandler: UserHandler
) {

    @Bean
    fun routeUser() = coRouter {
        "users".nest {
            POST("", userHandler::handleUserSignInRequest)
        }
    }
}