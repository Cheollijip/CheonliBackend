package com.example.springbackend.matjib.infrastructure.router.dtos

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class MatjibRequest(
    @NotNull
    val longitude: Double,
    @NotNull
    val latitude: Double,
    @NotNull
    val score: Double,
    @NotBlank
    val name: String,
    @NotBlank
    val description: String,
    @NotBlank
    val address: String
)