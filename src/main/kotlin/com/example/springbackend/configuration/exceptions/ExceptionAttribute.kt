package com.example.springbackend.configuration.exceptions

interface ExceptionAttribute {
    val errorMessage: String
    val statusCode: Int
}
