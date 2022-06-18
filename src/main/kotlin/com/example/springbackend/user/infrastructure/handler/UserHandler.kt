package com.example.springbackend.user.infrastructure.handler

import com.example.springbackend.configuration.validate.RequestBodyValidator
import com.example.springbackend.user.domain.api.UserApi
import com.example.springbackend.user.infrastructure.request.UserSignInRequest
import com.example.springbackend.user.infrastructure.request.UserSignUpRequest
import java.net.URI
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class UserHandler(
    private val userApi: UserApi,
    private val requestBodyValidator: RequestBodyValidator
) {
    suspend fun handleUserSignUpRequest(serverRequest: ServerRequest): ServerResponse {
        val userSignUpRequest = serverRequest.toUserSignUpRequest()
        requestBodyValidator.validate(userSignUpRequest)
        val userSignInResponse = userApi.userSignUp(userSignUpRequest)
        return ServerResponse.created(URI("/users")).bodyValueAndAwait(userSignInResponse)
    }

    private suspend fun ServerRequest.toUserSignUpRequest() =
        this.awaitBody<UserSignUpRequest>()

    suspend fun handleUserSignInRequest(serverRequest: ServerRequest): ServerResponse {
        val userSignInRequest = serverRequest.toUserSignInRequest()
        requestBodyValidator.validate(userSignInRequest)
        val userSignInResponse = userApi.userSignIn(userSignInRequest)
        return ServerResponse.created(URI("/users/tokens")).bodyValueAndAwait(userSignInResponse)
    }

    private suspend fun ServerRequest.toUserSignInRequest() =
        this.awaitBody<UserSignInRequest>()
}