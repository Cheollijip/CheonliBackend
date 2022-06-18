package com.example.springbackend.user.domain.exceptions

import com.example.springbackend.configuration.exceptions.BaseException

class UserAlreadyExistsException(
    errorMessage: String
): BaseException(errorMessage, 409)
