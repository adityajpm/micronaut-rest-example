package com.jpmorgan.labs.micronaut.rest.example

import org.apache.commons.lang3.StringUtils

@JvmInline
value class AccountNumber(val value: String) {
    init {
        require(StringUtils.isNumeric(value)) { "Account number must contain only numbers" }
        require(value.length == 8) { "Account number must 8 characters in length" }
    }
}