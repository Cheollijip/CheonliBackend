package com.example.springbackend.matjib.domain.spi

import com.example.springbackend.matjib.domain.Matjib
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.Query

interface MatjibSpi {
    suspend fun getMatjibs(schoolId: String): List<Matjib>
    suspend fun save(matjib: Matjib)
    suspend fun findById(matjibId: String): Matjib
}
