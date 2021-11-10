package com.jpmorgan.labs.micronaut.rest.example.application

import com.jpmorgan.labs.jvm.commons.domain.identity.Id
import com.jpmorgan.labs.jvm.commons.domain.identity.Ulid
import com.jpmorgan.labs.micronaut.rest.example.TransferDetails


interface Application {

    fun transferRequest(transferDetails : TransferDetails): Id<Ulid>

}