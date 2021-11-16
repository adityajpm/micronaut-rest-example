package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
class ManagementEndpointsTest {

    @Inject
    lateinit var server: EmbeddedServer

    private val client by lazy {
        HttpClient(Apache) {
            expectSuccess = false
            defaultRequest {
                url {
                    host = server.host
                    port = server.port
                }
            }
        }
    }

    @Test
    fun `successfully get the service readiness status`() = runBlocking {

        val response : HttpResponse =  client.get(server.url(readinessPath))

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }

    @Test
    fun `successfully get the service liveness status`() = runBlocking {

        val response : HttpResponse =  client.get(server.url(livenessPath))

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }

    @Test
    fun `successfully get the service Prometheus metrics`() = runBlocking {

        val response : HttpResponse =  client.get(server.url(metricsPath))

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }

    companion object {
        const val basePath = "/actuator"
        const val readinessPath = "$basePath/health/readiness"
        const val livenessPath = "$basePath/health/liveness"
        const val metricsPath = "$basePath/prometheus"
    }

    private fun EmbeddedServer.url(path : String) = "http://${host}:${port}$path"
}