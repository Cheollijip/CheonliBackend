package com.example.springbackend.school.infrastructure.repository

import com.example.springbackend.school.infrastructure.SchoolEntity
import org.bson.types.ObjectId
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface SchoolRepository : ReactiveCrudRepository<SchoolEntity, ObjectId> {
    suspend fun findBySchoolName(schoolName: String): Mono<SchoolEntity>
}
