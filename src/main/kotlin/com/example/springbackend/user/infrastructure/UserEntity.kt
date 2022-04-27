package com.example.springbackend.user.infrastructure

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class UserEntity(
    val code: String,
    val schoolId: String,
    @Id
    val id: String = ""
)
