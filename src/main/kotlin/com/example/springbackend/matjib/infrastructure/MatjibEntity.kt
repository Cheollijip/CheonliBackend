package com.example.springbackend.matjib.infrastructure

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
class MatjibEntity(
    val location: GeoJsonPoint,
    val name: String,
    val description: String,
    val schoolId: String,
    val scores: List<ScoreEntity>,
    val address: String,
    @MongoId
    var id: ObjectId? = null
)

class ScoreEntity(
    val userId: String,
    val score: Int
)
