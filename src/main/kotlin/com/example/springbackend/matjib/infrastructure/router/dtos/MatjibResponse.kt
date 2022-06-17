package com.example.springbackend.matjib.infrastructure.router.dtos

class MatjibResponse(
    val matjibId: String,
    val longitude: Double,
    val latitude: Double,
    val name: String,
    val address: String,
    val score: Double,
    val isScored: Boolean,
    val distance: Int,
    val description: String,
    val schoolName: String
)

class MatjibListResponse(
    val matjibs: List<MatjibResponse>
)