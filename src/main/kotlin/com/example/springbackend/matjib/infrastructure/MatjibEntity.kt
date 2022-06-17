package com.example.springbackend.matjib.infrastructure

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.mapping.Document

@Document
class MatjibEntity(
    @Id
    val id: String,
    val location: GeoJsonPoint,
    val name: String,
    val description: String,
    val schoolId: String,
    val scores: List<Score>
)

class Score(
    val userId: String,
    val score: Int
)
