package com.example.springbackend.user.infrastructure.spi

import com.example.springbackend.user.domain.UserDomain
import com.example.springbackend.user.domain.spi.UserRepositorySpi
import com.example.springbackend.user.infrastructure.UserEntity
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class UserRepositorySpiImpl(
    private val userRepository: UserRepository
) : UserRepositorySpi {
    override suspend fun saveOrGetUserDomainObject(userDomain: UserDomain): UserDomain {
        val id = if (userDomain.id == "") {
            ObjectId()
        } else {
            ObjectId(userDomain.id)
        }
        val userOrNull = userRepository.findById(id).awaitSingleOrNull()
        return userOrNull?.toUserDomain() ?: saveUser(userDomain)
    }

    private suspend fun saveUser(userDomain: UserDomain): UserDomain {
        val userEntity = userDomain.toUserEntity()
        val savedUserEntity = userRepository.save(userEntity).awaitSingle()
        return savedUserEntity.toUserDomain()
    }

    override suspend fun findById(userId: String): UserDomain? {
        return userRepository.findById(ObjectId(userId)).awaitSingleOrNull()?.toUserDomain()
    }

    private fun UserDomain.toUserEntity() =
        UserEntity(
            code = this.code,
            schoolId = this.schoolId
        )

    private fun UserEntity.toUserDomain() =
        UserDomain(
            code = this.code,
            schoolId = this.schoolId,
            id = this.id.toString()
        )
}