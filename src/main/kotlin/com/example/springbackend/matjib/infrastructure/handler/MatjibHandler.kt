package com.example.springbackend.matjib.infrastructure.handler

import com.example.springbackend.matjib.domain.Matjib
import com.example.springbackend.matjib.domain.api.MatjibApi
import com.example.springbackend.matjib.infrastructure.router.dtos.MatjibRequest
import java.net.URI
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class MatjibHandler(
    private val matjibApi: MatjibApi
) {
    suspend fun handleGetMatjibList(serverRequest: ServerRequest): ServerResponse {
        val matjibs = matjibApi.getOurMatjib()

        return ServerResponse.ok().bodyValueAndAwait(matjibs)
    }

    suspend fun handleSaveMatjib(serverRequest: ServerRequest): ServerResponse {
        val matjibRequest = serverRequest.awaitBody<MatjibRequest>()
        matjibApi.saveMatjib(matjibRequest)
        return ServerResponse.created(URI("/matjibs")).buildAndAwait()
    }
}