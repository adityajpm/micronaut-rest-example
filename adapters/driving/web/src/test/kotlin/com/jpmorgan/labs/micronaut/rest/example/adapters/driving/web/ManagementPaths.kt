package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web


const val basePath = "/actuator"
const val readinessPath = "$basePath/health/readiness"
const val livenessPath = "$basePath/health/liveness"
const val metricsPath = "$basePath/prometheus"
