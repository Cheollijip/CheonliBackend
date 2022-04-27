package com.example.springbackend.configuration.property

import com.example.springbackend.SpringBackendApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = [SpringBackendApplication::class])
class ConfigurationPropertyConfig
