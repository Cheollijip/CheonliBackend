package com.example.springbackend.school.infrastructure.repository

import com.example.springbackend.school.infrastructure.SchoolEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface SchoolRepository : ReactiveCrudRepository<SchoolEntity, String> {
    suspend fun findBySchoolName(schoolName: String): Mono<SchoolEntity>
}
