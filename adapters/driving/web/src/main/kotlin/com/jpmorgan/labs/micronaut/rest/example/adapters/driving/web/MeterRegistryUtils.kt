package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import io.micronaut.http.HttpResponse
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue
import kotlin.time.toJavaDuration

fun MeterRegistry.timer(name: String, status: Int): Timer = timer(name, "status", status.toString())

@OptIn(ExperimentalTime::class)
suspend fun <T> MeterRegistry.measure(callName: String, action: suspend () -> HttpResponse<T>) =
    measureTimedValue { action() }.also { timer(callName, it.value.status.code).record(it.duration.toJavaDuration()) }.value