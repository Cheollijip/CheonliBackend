package com.example.springbackend.user.infrastructure.repository

import com.example.springbackend.user.infrastructure.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ReactiveCrudRepository<UserEntity, String>
