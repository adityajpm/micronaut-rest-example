package com.jpmorgan.labs.micronaut.rest.example.application

import com.jpmorgan.labs.jvm.commons.domain.identity.Id
import com.jpmorgan.labs.jvm.commons.domain.identity.Ulid
import com.jpmorgan.labs.micronaut.rest.example.TransferDetails
import jakarta.inject.Singleton

@Singleton
class DelegationApplication : Application {

    override fun transferRequest(transferDetails: TransferDetails): Id<Ulid> {
        println("Processing  of $transferDetails")
        return Ulid.create()
    }

}