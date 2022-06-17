package com.example.springbackend.school.domain.api

import com.example.springbackend.school.domain.LocationDomain
import com.example.springbackend.school.domain.SchoolDomain
import com.example.springbackend.school.domain.exceptions.SchoolNotFoundException
import com.example.springbackend.school.domain.spi.LocationSpi
import com.example.springbackend.school.domain.spi.SchoolRepositorySpi
import org.springframework.stereotype.Service

@Service
class SchoolApiImpl(
    private val schoolRepositorySpi: SchoolRepositorySpi,
    private val locationSpi: LocationSpi
) : SchoolApi {
    override suspend fun findOrSaveSchool(schoolName: String): SchoolDomain {
        val locationOfSchoolName = getLocationOfSchoolNameOrThrowException(schoolName)

        return schoolRepositorySpi.findBySchoolNameOrNull(locationOfSchoolName.placeName)
            ?: createSchool(locationOfSchoolName)
    }

    private suspend fun getLocationOfSchoolNameOrThrowException(schoolName: String) =
        locationSpi.getFirstResultOfKeywordOrNull(schoolName)
            ?: throw SchoolNotFoundException("School Not Found")

    private suspend fun createSchool(locationDomain: LocationDomain): SchoolDomain {
        val schoolDomain = locationDomain.toSchoolDomain()
        return schoolRepositorySpi.saveSchool(schoolDomain)
    }

    private fun LocationDomain.toSchoolDomain() =
        SchoolDomain(
            latitude = this.latitude.toDouble(),
            longitude = this.longitude.toDouble(),
            schoolName = this.placeName
        )

}