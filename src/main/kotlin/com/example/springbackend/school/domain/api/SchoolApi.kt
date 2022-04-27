package com.example.springbackend.school.domain.api

import com.example.springbackend.school.domain.SchoolDomain

interface SchoolApi {
    suspend fun findOrSaveSchool(schoolName: String): SchoolDomain
}
