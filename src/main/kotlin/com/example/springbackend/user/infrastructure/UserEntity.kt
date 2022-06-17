package com.example.springbackend.user.infrastructure

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
class UserEntity(
    val code: String,
    val schoolId: String,
    @MongoId
    var id: ObjectId? = null
)
