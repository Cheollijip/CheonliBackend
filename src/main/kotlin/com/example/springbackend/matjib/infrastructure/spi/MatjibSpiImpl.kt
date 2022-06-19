package com.example.springbackend.matjib.infrastructure.spi

import com.example.springbackend.matjib.domain.Matjib
import com.example.springbackend.matjib.domain.Score
import com.example.springbackend.matjib.domain.spi.MatjibSpi
import com.example.springbackend.matjib.infrastructure.MatjibEntity
import com.example.springbackend.matjib.infrastructure.ScoreEntity
import com.example.springbackend.matjib.infrastructure.repository.MatjibRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.stereotype.Repository

@Repository
class MatjibSpiImpl(
    private val matjibRepository: MatjibRepository
): MatjibSpi {
    override suspend fun getMatjibs(schoolId: String): List<Matjib> {
        val schools = matjibRepository.findAllBySchoolId(schoolId)
        return schools.map { it.toDomain() }.toList()
    }

    override suspend fun save(matjib: Matjib) {
        matjibRepository.save(matjib.toEntity()).awaitSingle()
    }

    override suspend fun findById(matjibId: String): Matjib {
        return matjibRepository.findById(ObjectId(matjibId)).awaitSingle().toDomain()
    }

    override suspend fun delete(matjibId: String) {
        matjibRepository.deleteById(ObjectId(matjibId)).awaitSingle()
    }

    private fun MatjibEntity.toDomain() =
        Matjib(
            description = this.description,
            name = this.name,
            longitude = this.location.x,
            latitude = this.location.y,
            scores = this.scores.map { it.toDomain() }.toMutableList(),
            address = this.address,
            id = this.id.toString(),
            schoolId = this.schoolId
        )

    private fun ScoreEntity.toDomain() =
        Score(
            userId = this.userId,
            score = this.score
        )

    private fun Matjib.toEntity() =
        MatjibEntity(
            name = this.name,
            address = this.address,
            location = GeoJsonPoint(this.longitude, this.latitude),
            scores = this.scores.map { ScoreEntity(it.userId, it.score) },
            description = this.description,
            schoolId = this.schoolId
        )
}