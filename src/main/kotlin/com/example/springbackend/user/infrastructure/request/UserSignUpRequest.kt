package com.example.springbackend.user.infrastructure.request

import javax.validation.constraints.NotBlank

class UserSignUpRequest(
    @NotBlank
    val schoolName: String,

    @NotBlank
    val code: String
)
