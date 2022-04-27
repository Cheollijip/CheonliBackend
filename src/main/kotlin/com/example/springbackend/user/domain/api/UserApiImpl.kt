package com.example.springbackend.user.domain.api

import com.example.springbackend.school.domain.api.SchoolApi
import com.example.springbackend.user.domain.UserDomain
import com.example.springbackend.user.domain.spi.UserRepositorySpi
import com.example.springbackend.user.infrastructure.request.UserSignInRequest
import org.springframework.stereotype.Service

@Service
class UserApiImpl(
    private val userRepositorySpi: UserRepositorySpi,
    private val schoolApi: SchoolApi
) : UserApi {
    override suspend fun createUser(request: UserSignInRequest) {
        val school = schoolApi.findOrSaveSchool(request.schoolName)
        val userDomain = request.toUserDomain(school.id)
        userRepositorySpi.saveUserDomainObject(userDomain)
    }

    private fun UserSignInRequest.toUserDomain(schoolId: String) =
        UserDomain(
            code = this.code,
            schoolId = schoolId
        )
}