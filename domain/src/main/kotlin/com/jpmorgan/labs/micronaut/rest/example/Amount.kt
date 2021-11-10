package com.jpmorgan.labs.micronaut.rest.example

@JvmInline
value class Amount(val value: Int) {
    init {
        require(value > 0) { "Amount be must be greater than zero" }
    }
}

fun Int.asAmount() = Amount(this)