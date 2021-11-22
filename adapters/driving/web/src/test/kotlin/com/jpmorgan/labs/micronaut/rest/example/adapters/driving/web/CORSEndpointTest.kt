package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jpmorgan.labs.micronaut.rest.example.application.Application
import io.ktor.client.*
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
import io.mockk.mockk
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Confirms configuration applied by io.micronaut.http.server.cors.CorsFilter
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
private class CORSEndpointTest {

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
    fun `allows CORS options calls to work`() = runBlocking {

        val response = client.options<HttpResponse>(server.url(Api.Endpoints.Transfer.path)) {
            headers.append(HttpHeaders.Origin, "http://localhost:8080")
            headers.append(HttpHeaders.AccessControlRequestMethod, HttpMethod.Post.value)
//          headers.append(HttpHeaders.AccessControlRequestMethod, "header value")
        }

        assertThat(response.status).isEqualTo(HttpStatusCode.OK)
    }

    @ParameterizedTest
    @MethodSource("notAllowedHttpMethods")
    fun `disallowed method is not allowed`(httpMethod: HttpMethod) = runBlocking {

        val response = client.options<HttpResponse>(server.url(Api.Endpoints.Transfer.path)) {
            headers.append(HttpHeaders.Origin, "http://localhost:8080")
            headers.append(HttpHeaders.AccessControlRequestMethod, httpMethod.value)
//          headers.append(HttpHeaders.AccessControlRequestMethod, "header value")
        }

        assertThat(response.status).isEqualTo(HttpStatusCode.Forbidden)
    }

    private fun notAllowedHttpMethods(): Stream<Arguments> = (HttpMethod.all - HttpMethod.Get).map { Arguments.of(it) }.stream()
    private val HttpMethod.Companion.all: Set<HttpMethod> get() = setOf(Head, Options, Delete, Put, Patch, Get)

}