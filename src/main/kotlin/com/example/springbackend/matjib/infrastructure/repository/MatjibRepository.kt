package com.example.springbackend.matjib.infrastructure.repository

import com.example.springbackend.matjib.infrastructure.MatjibEntity
import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MatjibRepository : ReactiveCrudRepository<MatjibEntity, ObjectId> {
    fun findAllBySchoolId(schoolId: String): Flow<MatjibEntity>
}