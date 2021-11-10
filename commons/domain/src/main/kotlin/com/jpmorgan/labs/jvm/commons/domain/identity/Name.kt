package com.jpmorgan.labs.jvm.commons.domain.identity

import java.util.regex.Pattern

@JvmInline
value class Name private constructor(val value: String) : Comparable<Name> {

    init {
        require(value.isNotBlank())
        require(value.trim() == value)
    }

    override fun compareTo(other: Name) = value.compareTo(other.value)

    override fun toString() = "Name(value='$value')"

    companion object {

        fun create(value: String): Name = Name(value)
        private val whitespace = Pattern.compile("\\s")
    }
}

fun String.asName(): Name = Name.create(this)