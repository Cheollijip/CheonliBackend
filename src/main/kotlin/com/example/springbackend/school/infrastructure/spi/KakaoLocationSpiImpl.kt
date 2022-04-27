package com.example.springbackend.school.infrastructure.spi

import com.example.springbackend.school.domain.LocationDomain
import com.example.springbackend.school.domain.spi.LocationSpi
import com.example.springbackend.school.infrastructure.property.KakaoApiProperty
import com.example.springbackend.school.infrastructure.response.KakaoSearchResponse
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitExchangeOrNull
import org.springframework.web.reactive.function.client.bodyToMono

@Repository
class KakaoLocationSpiImpl(
    private val webClient: WebClient,
    private val kakaoApiProperty: KakaoApiProperty
) : LocationSpi {

    override suspend fun getFirstResultOfKeywordOrNull(keyword: String): LocationDomain? {
        val requestResult = buildSearchRequest(keyword)
        return requestResult.awaitExchangeOrNull {
            it.buildLocationDomain()
        }
    }

    private fun buildSearchRequest(keyword: String) =
        webClient.get()
            .uri {
                it.path("${kakaoApiProperty.baseUrl}${kakaoApiProperty.searchEndpoint}")
                    .queryParam("query", keyword)
                    .queryParam("size", 1)
                    .build()
            }
            .header(HttpHeaders.AUTHORIZATION, "${kakaoApiProperty.authorizationPrefix} ${kakaoApiProperty.restApiKey}")

    private suspend fun ClientResponse.buildLocationDomain(): LocationDomain? {
        val responseBody = this.bodyToMono<KakaoSearchResponse>().awaitSingleOrNull()
        return responseBody?.buildLocationDomain()
    }

    private fun KakaoSearchResponse.buildLocationDomain(): LocationDomain =
        LocationDomain(
            longitude = this.x,
            latitude = this.y,
            placeName = this.placeName
        )

}