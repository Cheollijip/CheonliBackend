package com.example.springbackend.configuration.exceptions.handler

data class ErrorResponse(
    val responseStatus: Int,
    val errorMessage: String
)
