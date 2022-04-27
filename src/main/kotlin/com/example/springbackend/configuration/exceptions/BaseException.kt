package com.example.springbackend.configuration.exceptions

abstract class BaseException(
    override val errorMessage: String,
    override val statusCode: Int
) : RuntimeException(errorMessage), ExceptionAttribute {
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
