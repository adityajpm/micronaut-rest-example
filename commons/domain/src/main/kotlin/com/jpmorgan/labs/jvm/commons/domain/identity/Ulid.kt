package com.jpmorgan.labs.jvm.commons.domain.identity

import de.huxhorn.sulky.ulid.ULID
import java.time.Instant
import kotlin.random.Random
import kotlin.random.asJavaRandom

class Ulid internal constructor(internal val ulid: ULID.Value) : Id<Ulid> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Ulid
        if (ulid != other.ulid) return false
        return true
    }

    override fun hashCode() = ulid.hashCode()

    override fun toString() = ulid.toString()

    companion object : Factory by RandomUlidFactory()

    interface Factory {

        fun create(timestamp: Instant = Instant.now()): Ulid

        fun fromString(value: String): Ulid

        fun fromBytes(data: ByteArray): Ulid

        fun nextMonotonic(previousId: Ulid): Ulid
    }
}

fun Ulid.Factory.newMonotonicSequence(initialTimestamp: Instant = Instant.now()): Sequence<Ulid> = generateSequence(create(initialTimestamp), ::nextMonotonic)

class RandomUlidFactory(random: Random = Random) : Ulid.Factory {

    private val generator: ULID = ULID(random.asJavaRandom())

    override fun create(timestamp: Instant): Ulid = Ulid(generator.nextValue(timestamp.toEpochMilli()))

    override fun fromString(value: String): Ulid = Ulid(ULID.parseULID(value))

    override fun fromBytes(data: ByteArray): Ulid = Ulid(ULID.fromBytes(data))

    override fun nextMonotonic(previousId: Ulid): Ulid = Ulid(generator.nextMonotonicValue(previousId.ulid))
}