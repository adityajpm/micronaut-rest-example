package com.jpmorgan.labs.micronaut.rest.example

import com.jpmorgan.labs.jvm.commons.domain.identity.Ulid

@JvmInline
value class UserId private constructor(val value: String){

    companion object {
        fun createFromULid() = UserId(Ulid.create().toString())
        fun createFromString(value: String) = UserId(value)
    }
}

fun String.asUserId() = UserId.createFromString(this)
