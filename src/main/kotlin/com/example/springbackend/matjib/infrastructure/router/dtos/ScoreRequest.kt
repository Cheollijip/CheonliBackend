package com.example.springbackend.matjib.infrastructure.router.dtos

import javax.validation.constraints.NotNull

class ScoreRequest(
    @NotNull
    val score: Double
)