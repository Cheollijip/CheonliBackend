package com.example.springbackend.user.infrastructure.request

import javax.validation.constraints.NotBlank

class UserSignInRequest(
    @NotBlank
    val code: String
)
