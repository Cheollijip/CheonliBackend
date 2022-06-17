package com.example.springbackend.matjib.infrastructure.spi

import com.example.springbackend.matjib.domain.Matjib
import com.example.springbackend.matjib.domain.spi.MatjibSpi
import com.example.springbackend.matjib.infrastructure.repository.MatjibRepository

class MatjibSpiImpl(
    private val matjibRepository: MatjibRepository
): MatjibSpi {
    override fun getMatjibs(schoolId: String): List<Matjib> {
//        matjibRepository.find
        TODO()
    }
}