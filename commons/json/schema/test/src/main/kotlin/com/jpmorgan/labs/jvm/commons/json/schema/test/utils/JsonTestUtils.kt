package com.jpmorgan.labs.jvm.commons.json.schema.test.utils

import assertk.Assert
import assertk.assertions.containsAll
import assertk.assertions.containsOnly
import assertk.assertions.support.expected
import assertk.assertions.support.show
import org.everit.json.schema.Schema
import org.everit.json.schema.ValidationException
import org.json.JSONObject

fun Assert<JSONObject>.compliesWith(schema: Schema) = given { actual ->

    try {
        schema.validate(actual)
    } catch (e: ValidationException) {
        expected("JSON that complies with schema at :${show(schema.location)}/${schema.title} but there were errors:${show(e.allMessages)}")
    }
}