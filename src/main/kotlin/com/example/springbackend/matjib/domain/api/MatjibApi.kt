package com.example.springbackend.matjib.domain.api

import com.example.springbackend.matjib.domain.Matjib

interface MatjibApi {
    fun getOurMatjib(): List<Matjib>
}
