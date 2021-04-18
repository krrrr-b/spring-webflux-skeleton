package com.skeleton.webflux.client.unknown.config

import com.skeleton.webflux.client.unknown.client.UnknownClient
import com.skeleton.webflux.client.FeignClientConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactivefeign.spring.config.ReactiveFeignClientsConfiguration

@Configuration
@AutoConfigureBefore(ReactiveFeignClientsConfiguration::class)
class UnknownClientConfiguration {
    companion object {
        const val UNKNOWN_CLIENT = "UNKNOWN_CLIENT"
    }

    @Bean(UNKNOWN_CLIENT)
    fun unknownClient(): UnknownClient {
        return FeignClientConfiguration<UnknownClient>()
            .reactiveFeignBuilder()
            .target(UnknownClient::class.java, "endPoint")
    }
}
