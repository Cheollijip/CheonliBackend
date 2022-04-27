package com.example.springbackend.configuration.exceptions

class InternalServerError(
    errorMessage: String,
    statusCode: Int = 500
) : BaseException(errorMessage, statusCode) {
    companion object {
        const val UNEXPECTED_EXCEPTION = "Unexpected Error Occurred"
    }
}
