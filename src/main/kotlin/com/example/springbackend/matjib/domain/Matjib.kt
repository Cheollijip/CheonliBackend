package com.example.springbackend.matjib.domain

class Matjib(
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String,
    val scores: List<Score>,
    val address: String,
    val id: String = "",
    val schoolId: String
)

class Score(
    val userId: String,
    val score: Int
)