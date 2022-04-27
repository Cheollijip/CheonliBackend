package com.example.springbackend.configuration.validate

import com.example.springbackend.configuration.exceptions.BaseException

class BadRequestException(
    errorMessage: String
) : BaseException(errorMessage, 400)
