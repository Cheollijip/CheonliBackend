package com.example.springbackend.school.infrastructure.spi

import com.example.springbackend.school.domain.SchoolDomain
import com.example.springbackend.school.domain.spi.SchoolRepositorySpi
import com.example.springbackend.school.infrastructure.SchoolEntity
import com.example.springbackend.school.infrastructure.repository.SchoolRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.stereotype.Repository

@Repository
class SchoolRepositorySpiImpl(
    private val schoolRepository: SchoolRepository
) : SchoolRepositorySpi {
    override suspend fun findBySchoolNameOrNull(name: String): SchoolDomain? {
        val schoolEntity = schoolRepository.findBySchoolName(name).awaitSingleOrNull()
        return schoolEntity?.toDomainObject()
    }

    override suspend fun saveSchool(schoolDomain: SchoolDomain): SchoolDomain {
        val schoolEntity = schoolDomain.toSchoolEntity()
        val savedSchoolEntity = schoolRepository.save(schoolEntity).awaitSingle()
        return savedSchoolEntity.toDomainObject()
    }

    override suspend fun findById(schoolId: String): SchoolDomain? {
        return schoolRepository.findById(ObjectId(schoolId)).awaitSingle().toDomainObject()
    }

    private fun SchoolDomain.toSchoolEntity() =
        SchoolEntity(
            schoolName = this.schoolName,
            location = GeoJsonPoint(this.longitude, this.latitude)
        )

    private fun SchoolEntity.toDomainObject() =
        SchoolDomain(
            schoolName = this.schoolName,
            latitude = this.location.y,
            longitude = this.location.x,
            id = this.id.toString()
        )
}
