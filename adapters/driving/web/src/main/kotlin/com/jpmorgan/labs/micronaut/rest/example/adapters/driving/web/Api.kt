package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import com.jpmorgan.labs.jvm.commons.json.schema.utils.jsonSchemaUnderRootFolder
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Post
import org.everit.json.schema.Schema

object Api {

    object Endpoints {
        interface Transfer : Endpoint {

            companion object {

                const val path = "/transfer"
            }

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


