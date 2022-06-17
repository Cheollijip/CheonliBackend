package com.example.springbackend.matjib.infrastructure.repository

import com.example.springbackend.matjib.infrastructure.MatjibEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MatjibRepository: ReactiveMongoRepository<MatjibEntity, String> {
    fun findAllBy()
}