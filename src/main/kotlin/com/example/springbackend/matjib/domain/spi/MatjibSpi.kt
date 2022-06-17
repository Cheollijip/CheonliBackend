package com.example.springbackend.matjib.domain.spi

import com.example.springbackend.matjib.domain.Matjib

interface MatjibSpi {
    fun getMatjibs(schoolId: String): List<Matjib>
}
