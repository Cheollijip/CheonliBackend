package com.example.springbackend.school.domain.spi

import com.example.springbackend.school.domain.SchoolDomain

interface SchoolRepositorySpi {
    suspend fun findBySchoolNameOrNull(name: String): SchoolDomain?
    suspend fun saveSchool(schoolDomain: SchoolDomain): SchoolDomain
}