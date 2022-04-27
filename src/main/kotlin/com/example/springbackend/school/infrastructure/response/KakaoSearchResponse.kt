package com.example.springbackend.school.infrastructure.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

class KakaoSearchResponse(
    val documents: List<DocumentsResponse>
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class DocumentsResponse(
    val placeName: String,
    val x: BigDecimal,
    val y: BigDecimal
)
