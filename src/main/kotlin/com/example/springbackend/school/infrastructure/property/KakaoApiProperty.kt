package com.example.springbackend.school.infrastructure.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "kakao.property")
@ConstructorBinding
class KakaoApiProperty(
    val restApiKey: String,
    val baseUrl: String,
    val searchEndpoint: String,
    val addressEndpoint: String,
    val authorizationPrefix: String
)