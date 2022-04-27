package com.example.springbackend.user.domain.spi

import com.example.springbackend.user.domain.UserDomain

interface UserRepositorySpi {
    suspend fun saveUserDomainObject(userDomain: UserDomain): UserDomain
}
