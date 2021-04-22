package com.skeleton.webflux.client.unknown.client

import feign.Headers
import org.springframework.web.bind.annotation.GetMapping
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@Headers("Content-Type: application/json")
@ReactiveFeignClient(name = "unknown2")
interface Unknown2Client {
    @GetMapping
    fun test(): Mono<String>
}
