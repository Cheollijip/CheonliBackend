package com.example.springbackend.matjib.domain.api

import com.example.springbackend.matjib.infrastructure.router.dtos.MatjibRequest
import com.example.springbackend.matjib.infrastructure.router.dtos.MatjibResponse

interface MatjibApi {
    suspend fun getOurMatjib(): List<MatjibResponse>
    suspend fun saveMatjib(matjib: MatjibRequest)
    suspend fun saveScore(matjibId: String, score: Double): Double
}
