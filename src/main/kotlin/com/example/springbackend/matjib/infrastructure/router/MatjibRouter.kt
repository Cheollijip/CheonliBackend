package com.example.springbackend.matjib.infrastructure.router

import com.example.springbackend.matjib.infrastructure.handler.MatjibHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class MatjibRouter(
    private val matjibHandler: MatjibHandler
) {
    @Bean
    fun matjibBaseRouter() = coRouter {
        "/matjibs".nest {
            GET("", matjibHandler::handleGetMatjibList)
            POST("", matjibHandler::handleSaveMatjib)
        }
    }
}