package com.example.springbackend.matjib.domain

class Matjib(
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String,
    val scores: MutableList<Score>,
    val address: String,
    val id: String = "",
    val schoolId: String
) {
    fun addScore(userId: String, score: Double) {
        if (scores.firstOrNull { it.userId == userId } == null) {
            scores.add(Score(userId, score))
        }
    }
}

class Score(
    val userId: String,
    val score: Double
)