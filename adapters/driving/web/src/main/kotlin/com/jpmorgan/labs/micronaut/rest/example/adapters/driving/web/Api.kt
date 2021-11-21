package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import com.jpmorgan.labs.jvm.commons.json.schema.utils.jsonSchemaUnderRootFolder
import com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web.Api.Endpoints.Transfer.Metrics.callName
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.everit.json.schema.Schema
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue
import kotlin.time.toJavaDuration

object Api {


    object Endpoints {
        interface Transfer : Endpoint {

            val metrics: MeterRegistry

            companion object {

                const val path = "/transfer"
            }

            @Post(consumes = [MediaType.APPLICATION_JSON])
            suspend fun transferRequestEndpoint(request: HttpRequest<String>): HttpResponse<String> = metrics.measure(callName) { transferRequest(request) }
            suspend fun transferRequest(request: HttpRequest<String>): HttpResponse<String>

            object Metrics {
                const val callName = "transfer_request"
            }
        }
    }

    object Resources {

        object JsonSchemas {

            private const val standardRootDirectory = "META-INF/swagger/api/schemas/json"

            fun load(schemaPath: String): Schema = jsonSchemaUnderRootFolder(schemaPathFromRootFolder = schemaPath, rootFolderPath = standardRootDirectory)
        }

        object TransferDetails {

            val jsonSchema: Schema by lazy { JsonSchemas.load("TransferDetails.json") }

            object Fields {

                const val userId = "userId"
                const val amount = "amount"
                const val accountFrom = "accountFrom"
                const val accountTo = "accountTo"
            }
        }
    }

}



