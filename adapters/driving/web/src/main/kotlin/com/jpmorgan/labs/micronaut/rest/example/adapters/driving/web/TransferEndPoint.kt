package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import com.jpmorgan.labs.micronaut.rest.example.application.Application
import io.micronaut.context.annotation.Context
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.Controller
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import mu.KLogging
import org.everit.json.schema.ValidationException
import org.json.JSONObject

@Context
@Controller(Api.Endpoints.Transfer.path)
class TransferEndPoint(private val application: Application) : Api.Endpoints.Transfer {

    @PostConstruct
    fun start() = logger.info { "Started" }

    @PreDestroy
    fun stop() = logger.info { "Stopped" }

    override val path = Api.Endpoints.Transfer.path

    override suspend fun transferRequest(request: HttpRequest<String>): HttpResponse<String> {
        val json = JSONObject(request.body.get())

        val validationResult = validateJson(json)

        return if (validationResult.isSuccess) {
            val transferId = application.transferRequest(json.toTransferRequest()).toString()
            created(transferId)
        } else {
            badRequest(validationResult.exceptionOrNull()!!.message)
        }
    }

    private fun validateJson(json: JSONObject): Result<JSONObject> = kotlin.runCatching {
        try {
            Api.Resources.TransferDetails.jsonSchema.validate(json)
            json
        } catch (validationException: ValidationException) {
            error(validationException.allMessages.joinToString("\n"))
        }
    }

    companion object : KLogging()


}