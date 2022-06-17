package com.example.springbackend.matjib.domain

import org.springframework.data.mongodb.core.geo.GeoJsonPoint

class Matjib(
    val location: GeoJsonPoint,
    val name: String,
    val description: String,
    val scores: List<Score>
)

class Score(
    val userId: String,
    val score: Int
)