package com.example.springbackend.school.infrastructure.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

class KakaoAddressResponse(
    val documents: List<AddressResponse>
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class AddressResponse(
    val roadAddress: RoadAddressResponse
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class RoadAddressResponse(
    val addressName: String
)
