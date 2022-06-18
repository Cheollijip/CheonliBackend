package com.example.springbackend.user.domain.spi

import com.example.springbackend.user.domain.UserDomain

interface UserRepositorySpi {
    suspend fun saveUser(userDomain: UserDomain): UserDomain
    suspend fun findById(userId: String): UserDomain?
    suspend fun findByCode(code: String): UserDomain?
}
