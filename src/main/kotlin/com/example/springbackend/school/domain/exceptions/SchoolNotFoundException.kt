package com.example.springbackend.school.domain.exceptions

import com.example.springbackend.configuration.exceptions.BaseException

class SchoolNotFoundException(
    errorMessage: String
): BaseException(errorMessage, 404)
