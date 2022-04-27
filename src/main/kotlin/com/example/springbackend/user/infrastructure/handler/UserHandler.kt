package com.example.springbackend.user.infrastructure.handler

import com.example.springbackend.user.domain.api.UserApi
import com.example.springbackend.user.domain.api.UserApiImpl
import com.example.springbackend.user.infrastructure.request.UserSignInRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class UserHandler(
    private val userApi: UserApi
) {
    suspend fun handleUserSignInRequest(serverRequest: ServerRequest): ServerResponse {
        val userSignInRequest = serverRequest.toUserSignInRequest()


    }

    private suspend fun ServerRequest.toUserSignInRequest() =
        this.awaitBody<UserSignInRequest>()
}