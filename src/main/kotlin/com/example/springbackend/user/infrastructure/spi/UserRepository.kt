package com.example.springbackend.user.infrastructure.spi

import com.example.springbackend.user.infrastructure.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<UserEntity, ObjectId> {
    fun findByCode(code: String): Mono<UserEntity>
}
