package com.example.springbackend.school.infrastructure

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
class SchoolEntity(
    val schoolName: String,
    val location: GeoJsonPoint,
    @MongoId
    var id: ObjectId? = null
)
