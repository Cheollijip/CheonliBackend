package com.example.springbackend.user.domain.api

import com.example.springbackend.user.infrastructure.request.UserSignInRequest
import com.example.springbackend.user.infrastructure.response.UserSignInResponse

interface UserApi {
    suspend fun userSignIn(request: UserSignInRequest): UserSignInResponse
}
