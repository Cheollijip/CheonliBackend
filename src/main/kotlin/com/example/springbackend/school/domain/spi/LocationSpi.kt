package com.example.springbackend.school.domain.spi

import com.example.springbackend.school.domain.LocationDomain

interface LocationSpi {
    suspend fun getFirstResultOfKeywordOrNull(keyword: String): LocationDomain?
    suspend fun getAddressFromCoordinate(longitude: Double, latitude: Double): String?
}