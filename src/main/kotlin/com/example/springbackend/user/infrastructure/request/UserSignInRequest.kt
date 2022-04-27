package com.example.springbackend.user.infrastructure.request

import javax.validation.constraints.NotBlank

class UserSignInRequest(
    @NotBlank
    val schoolName: String,

    @NotBlank
    val code: String
)
