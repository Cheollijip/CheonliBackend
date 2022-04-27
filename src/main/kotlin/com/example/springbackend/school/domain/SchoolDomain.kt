package com.example.springbackend.school.domain

import java.math.BigDecimal

class SchoolDomain(
    val schoolName: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val id: String = ""
)
