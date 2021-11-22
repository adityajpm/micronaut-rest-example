package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jpmorgan.labs.jvm.commons.domain.identity.Ulid
import com.jpmorgan.labs.micronaut.rest.example.AccountNumber
import com.jpmorgan.labs.micronaut.rest.example.Amount
import com.jpmorgan.labs.micronaut.rest.example.TransferDetails
import com.jpmorgan.labs.micronaut.rest.example.UserId
import com.jpmorgan.labs.micronaut.rest.example.application.Application
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.micrometer.core.instrument.MeterRegistry
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Replaces
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.coEvery
import io.mockk.mockk
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
@MicronautTest
private class MetricsEndpointTest {

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

    private val application: Application = mockk()

    @MockBean
    @Replaces(bean = Application::class, qualifier = [Primary::class])
    fun application(): Application = application

    @Inject
    private lateinit var metrics: MeterRegistry

    @BeforeEach
    fun beforeEach() = metrics.clear()


    @Test
    fun `successfully get the service Prometheus metrics`() = runBlocking {

        val response : HttpResponse =  client.get(server.url(metricsPath))

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }


    @Test
    fun `creating a transfer request increases the call count`() = runBlocking {
        val transferDetails = TransferDetails(UserId.createFromULid(), Amount(10), AccountNumber("12345678"), AccountNumber("12345679"))
        val transferRequestJson = transferDetails.toJson()
        val newTransferRequestId = Ulid.create()
        coEvery { application.transferRequest(transferDetails) } returns newTransferRequestId

        client.post<HttpResponse>(server.url(Api.Endpoints.Transfer.path)){
            contentType(ContentType.Application.Json)
            body = transferRequestJson.toString()
        }
        client.post<HttpResponse>(server.url(Api.Endpoints.Transfer.path)){
            contentType(ContentType.Application.Json)
            body = transferRequestJson.toString()
        }

        val response : HttpResponse =  client.get(server.url(metricsPath))

        val measurements = response.receive<String>().parseAsPrometheusMetrics()

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
        val transferRequestCountMeasurement = measurements.filter { it.isForCall(Api.Endpoints.Transfer.Metrics.callName) }.filter(MetricMeasurement::isCount).single()
        assertThat(transferRequestCountMeasurement.value.toDouble()).isEqualTo(2.0)
    }

    private fun String.parseAsPrometheusMetrics(): Sequence<MetricMeasurement> = split("\n").asSequence().filterNot { it.startsWith("#") }.filterNot { it.isEmpty() }.map { it.substring(0, it.lastIndexOf(" ")) to it.substring(it.lastIndexOf(" ") + 1, it.length) }.map { MetricMeasurement(it.first, it.second) }

    private data class MetricMeasurement(val key: String, val value: String) {

        fun isForCall(callName: String): Boolean = key.startsWith(callName)

        fun isCount(): Boolean = "_count" in key

    }
}