package com.example.springbackend.user.domain.api

import com.example.springbackend.school.domain.api.SchoolApi
import com.example.springbackend.user.domain.UserDomain
import com.example.springbackend.user.domain.exceptions.UserNotFoundException
import com.example.springbackend.user.domain.spi.JwtTokenProviderSpi
import com.example.springbackend.user.domain.spi.UserRepositorySpi
import com.example.springbackend.user.infrastructure.request.UserSignInRequest
import com.example.springbackend.user.infrastructure.request.UserSignUpRequest
import com.example.springbackend.user.infrastructure.response.UserSignInResponse
import org.springframework.stereotype.Service

@Service
class UserApiImpl(
    private val userRepositorySpi: UserRepositorySpi,
    private val schoolApi: SchoolApi,
    private val jwtTokenProviderSpi: JwtTokenProviderSpi
) : UserApi {
    override suspend fun userSignUp(request: UserSignUpRequest): UserSignInResponse {
        val school = schoolApi.findOrSaveSchool(request.schoolName)
        val userDomain = request.toUserDomain(school.id)
        val userDomainFromDB = userRepositorySpi.saveUser(userDomain)
        val accessToken = jwtTokenProviderSpi.generateToken(userDomainFromDB.id)
        return UserSignInResponse(accessToken)
    }

    private fun UserSignUpRequest.toUserDomain(schoolId: String) =
        UserDomain(
            code = this.code,
            schoolId = schoolId
        )

    override suspend fun userSignIn(request: UserSignInRequest): UserSignInResponse {
        val user = userRepositorySpi.findByCode(request.code) ?: throw UserNotFoundException("User Not Found")
        val accessToken = jwtTokenProviderSpi.generateToken(user.id)
        return UserSignInResponse(accessToken)
    }
}