package com.example.springbackend.user.domain.exceptions

import com.example.springbackend.configuration.exceptions.BaseException

class UserNotFoundException(
    errorMessage: String
): BaseException(errorMessage, 404)
