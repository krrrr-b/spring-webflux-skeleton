package com.skeleton.webflux.internal.api.config.resilience

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreaker.StateTransition
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnFailureRateExceededEvent
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnSlowCallRateExceededEvent
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomResilienceRegistryEventConsumer {
    companion object {
        const val CUSTOM_RESILIENCE_CIRCUIT_BREAKER_EVENT_CONSUMER = "CUSTOM_RESILIENCE_CIRCUIT_BREAKER_EVENT_CONSUMER"
    }

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val emoji = "\uD83D\uDE21\uD83D\uDE21\uD83D\uDE21\uD83D\uDE21\uD83D\uDE21"

    fun sendWarningMessage(messages: Map<String, Any>) {
        logger.warn("$emoji $messages")
    }

    fun sendErrorMessage(messages: Map<String, Any>) {
        logger.error("$emoji $messages")
    }

    @Bean(CUSTOM_RESILIENCE_CIRCUIT_BREAKER_EVENT_CONSUMER)
    fun customResilienceCircuitBreakerEventConsumer(): RegistryEventConsumer<CircuitBreaker> {
        return object : RegistryEventConsumer<CircuitBreaker> {
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<CircuitBreaker>) {
                entryAddedEvent
                    .addedEntry
                    .eventPublisher
                    .onFailureRateExceeded { onFailureRateExceeded(it) }
                    .onSlowCallRateExceeded { onSlowCallRateExceeded(it) }
                    .onStateTransition { onStateTransition(it) }
            }

            private fun onFailureRateExceeded(event: CircuitBreakerOnFailureRateExceededEvent) {
                sendErrorMessage(
                    mapOf(
                        "circuit_breaker_name" to event.circuitBreakerName,
                        "event_type" to event.eventType,
                        "message" to "$emoji ???????????? / ????????? ${event.failureRate}"
                    )
                )
            }

            private fun onSlowCallRateExceeded(event: CircuitBreakerOnSlowCallRateExceededEvent) {
                sendErrorMessage(
                    mapOf(
                        "circuit_breaker_name" to event.circuitBreakerName,
                        "event_type" to event.eventType,
                        "message" to "$emoji ???????????? / ??????????????? ${event.slowCallRate}"
                    )
                )
            }

            private fun onStateTransition(event: CircuitBreakerOnStateTransitionEvent) {
                when (event.stateTransition) {
                    StateTransition.CLOSED_TO_OPEN, StateTransition.HALF_OPEN_TO_OPEN -> sendErrorMessage(
                        mapOf(
                            "circuit_breaker_name" to event.circuitBreakerName,
                            "event_type" to event.eventType,
                            "message" to "$emoji ????????? ???????????? CIRCUIT ${event.stateTransition} ???????????????."
                        )
                    )

                    StateTransition.OPEN_TO_HALF_OPEN -> sendErrorMessage(
                        mapOf(
                            "circuit_breaker_name" to event.circuitBreakerName,
                            "event_type" to event.eventType,
                            "message" to "$emoji CIRCUIT ${event.stateTransition} ??????????????? ????????????."
                        )
                    )

                    StateTransition.OPEN_TO_CLOSED -> sendErrorMessage(
                        mapOf(
                            "circuit_breaker_name" to event.circuitBreakerName,
                            "event_type" to event.eventType,
                            "message" to "\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00 CIRCUIT ????????????????????????"
                        )
                    )

                    else -> sendWarningMessage(
                        mapOf(
                            "circuit_breaker_name" to event.circuitBreakerName,
                            "event_type" to event.eventType,
                        )
                    )
                }
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<CircuitBreaker>) {}
            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<CircuitBreaker>) {}
        }
    }
}
