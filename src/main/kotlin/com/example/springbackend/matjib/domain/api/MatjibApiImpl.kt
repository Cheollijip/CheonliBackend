package com.example.springbackend.matjib.domain.api

import com.example.springbackend.matjib.domain.Matjib
import com.example.springbackend.matjib.domain.Score
import com.example.springbackend.matjib.domain.spi.MatjibSpi
import com.example.springbackend.matjib.infrastructure.router.dtos.MatjibRequest
import com.example.springbackend.matjib.infrastructure.router.dtos.MatjibResponse
import com.example.springbackend.school.domain.SchoolDomain
import com.example.springbackend.school.domain.spi.SchoolRepositorySpi
import com.example.springbackend.user.domain.UserDomain
import com.example.springbackend.user.domain.spi.UserRepositorySpi
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service

@Service
class MatjibApiImpl(
    private val matjibSpi: MatjibSpi,
    private val userRepositorySpi: UserRepositorySpi,
    private val schoolRepositorySpi: SchoolRepositorySpi
) : MatjibApi {
    override suspend fun getOurMatjib(): List<MatjibResponse> {
        val userId = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name
        val user = userRepositorySpi.findById(userId) ?: TODO()
        val school = schoolRepositorySpi.findById(user.schoolId)!!
        val matjibs = matjibSpi.getMatjibs(school.id)
        return matjibs.map { it.toMatjibResponse(school, user) }
    }

    private fun Matjib.toMatjibResponse(school: SchoolDomain, user: UserDomain): MatjibResponse {
        return MatjibResponse(
            description = this.description,
            address = this.address,
            name = this.name,
            schoolName = school.schoolName,
            longitude = this.longitude,
            latitude = this.latitude,
            score = this.scores.sumOf { it.score }.toDouble().div(this.scores.size),
            matjibId = this.id,
            isScored = this.scores.firstOrNull { it.userId == user.id } != null,
            distance = getDistance(GeoJsonPoint(school.longitude, school.latitude), GeoJsonPoint(this.longitude, this.latitude))
        )
    }

    private fun getDistance(geoJsonPoint1: GeoJsonPoint, geoJsonPoint2: GeoJsonPoint): Int {
        val theta = geoJsonPoint1.x - geoJsonPoint2.x
        return (rad2deg(
            acos(
                ((sin(deg2rad(geoJsonPoint1.y)) * sin(deg2rad(geoJsonPoint2.y))) +
                        (cos(deg2rad(geoJsonPoint1.y)) * cos(deg2rad(geoJsonPoint2.y)) * cos(deg2rad(theta))))
            )
        ) * 60 * 1.1515 * 1609.344).toInt()
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180 / Math.PI
    }

    override suspend fun saveMatjib(matjib: MatjibRequest) {
        val userId = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name
        val user = userRepositorySpi.findById(userId) ?: TODO()
        val matjibDomain = Matjib(
            latitude = matjib.latitude,
            longitude = matjib.longitude,
            schoolId = user.schoolId,
            description = matjib.description,
            scores = listOf(Score(userId, matjib.score)).toMutableList(),
            address = matjib.address,
            name = matjib.name,
        )
        matjibSpi.save(matjibDomain)
    }

    override suspend fun saveScore(matjibId: String, score: Double): Double {
        val matjib = matjibSpi.findById(matjibId)
        val userId = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name
        matjib.addScore(userId, score)
        matjibSpi.save(matjib)
        return matjib.scores.sumOf { it.score }.div(matjib.scores.size)
    }
}