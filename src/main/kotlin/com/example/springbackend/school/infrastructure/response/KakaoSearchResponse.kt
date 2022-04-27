package com.example.springbackend.school.infrastructure.response

import com.fasterxml.jackson.annotation.JsonRootName
import java.math.BigDecimal

@JsonRootName("documents")
class KakaoSearchResponse(
    val placeName: String,
    val x: BigDecimal,
    val y: BigDecimal
)
