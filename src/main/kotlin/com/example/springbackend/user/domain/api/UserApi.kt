package com.example.springbackend.user.domain.api

import com.example.springbackend.user.infrastructure.request.UserSignInRequest

interface UserApi {
    suspend fun createUser(request: UserSignInRequest)
}