package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import com.jpmorgan.labs.jvm.commons.json.schema.utils.jsonSchemaUnderRootFolder
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

@OpenAPIDefinition(
    info = Info(
        title = "TransferService",
        version = "0.0.1",
        description = "A example service to allow transfer requests to be made."
    )
)

object Api {

    object Endpoints {
        interface Transfer : Endpoint {

            companion object {

                const val path = "/transfer"
            }

            @Operation(summary = "Transfer Request")
            @RequestBody(
                description = "Details of the Item to be created",
                content = [Content(
                    schema = io.swagger.v3.oas.annotations.media.Schema(ref = "api/schemas/json/TransferDetails.json"),
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = [
                        ExampleObject( value = """
                            {
                                "userId":"01FM4JE6DVQ9MP08YREJRW6AYZ",
                                "amount":10,
                                "accountFrom":"12345678",
                                "accountTo":"12345679"
                            }
                        """
                        )
                    ]
                )]
            )
            @ApiResponse(
                responseCode = "201",
                content = [ Content(
                    schema = io.swagger.v3.oas.annotations.media.Schema( name = "String" )
                )]
            )

            @Post(consumes = [MediaType.APPLICATION_JSON])
            suspend fun transferRequest(request: HttpRequest<String>): HttpResponse<String>
        }
    }

    object Resources {

        object JsonSchemas {

            private const val standardRootDirectory = "api/schemas/json"

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


