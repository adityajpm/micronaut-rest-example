package com.jpmorgan.labs.micronaut.rest.example.application

import com.jpmorgan.labs.jvm.commons.domain.identity.Id
import com.jpmorgan.labs.jvm.commons.domain.identity.Ulid
import com.jpmorgan.labs.micronaut.rest.example.TransferDetails
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton
import mu.KLogging

@Singleton
class DelegationApplication(@Property(name = "service.application.message") val transferMessage: String) : Application {

    override fun transferRequest(transferDetails: TransferDetails): Id<Ulid> {
        logger.info("$transferMessage $transferDetails")
        return Ulid.create()
    }

    companion object : KLogging()
}