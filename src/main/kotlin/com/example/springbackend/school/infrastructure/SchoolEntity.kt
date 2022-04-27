package com.example.springbackend.school.infrastructure

import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class SchoolEntity(
    val schoolName: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    @Id
    val id: String = ""
)
