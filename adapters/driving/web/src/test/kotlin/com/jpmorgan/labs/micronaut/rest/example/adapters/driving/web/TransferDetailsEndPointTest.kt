package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import com.jpmorgan.labs.jvm.commons.domain.identity.Ulid
import com.jpmorgan.labs.jvm.commons.json.schema.test.utils.compliesWith
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
import io.ktor.util.*
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Replaces
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
class TransferDetailsEndPointTest {

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

    @Test
    fun `creating a new transfer request works`()  = runBlocking {
        val transferDetails = TransferDetails(UserId.createFromULid(), Amount(10), AccountNumber("12345678"), AccountNumber("12345679"))
        val transferRequestJson = transferDetails.toJson()
        assertThat(transferRequestJson).compliesWith(Api.Resources.TransferDetails.jsonSchema)
        val newTransferRequestId = Ulid.create()
        coEvery { application.transferRequest(transferDetails) } returns newTransferRequestId

        val response: HttpResponse = client.post("http://${server.host}:${server.port}/${Api.Endpoints.Transfer.path}"){
            contentType(ContentType.Application.Json)
            body = transferRequestJson.toString()
        }
        val responseContent = response.receive<String>()

        assertThat(response.status).isEqualTo(HttpStatusCode.Created)
        assertThat(responseContent).isEqualTo(newTransferRequestId.toString())
    }

    @Test
    fun `creating a new person fails as expected when a missing mandatory field is omitted`() = runBlocking {
        val transferDetails = TransferDetails(UserId.createFromULid(), Amount(10), AccountNumber("12345678"), AccountNumber("12345679"))
        val transferRequestJson = transferDetails.toJson().apply {
            remove(Api.Resources.TransferDetails.Fields.amount)
        }

        val response: HttpResponse = client.post("http://${server.host}:${server.port}/${Api.Endpoints.Transfer.path}"){
            contentType(ContentType.Application.Json)
            body = transferRequestJson.toString()
        }
        val responseContent = response.receive<String>()

        assertThat(response.status).isEqualTo(HttpStatusCode.BadRequest)
        assertThat(responseContent).contains(Api.Resources.TransferDetails.Fields.amount)
    }

    @MockBean
    @Replaces(bean = Application::class, qualifier = [Primary::class])
    fun application(): Application = application

}
