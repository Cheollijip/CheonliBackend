package com.example.springbackend.matjib.infrastructure.router.dtos

class MatjibRequest(
    val longitude: Double,
    val latitude: Double,
    val score: Double,
    val name: String,
    val description: String,
    val address: String
)