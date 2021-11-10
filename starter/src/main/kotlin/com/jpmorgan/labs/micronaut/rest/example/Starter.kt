package com.jpmorgan.labs.micronaut.rest.example

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.Micronaut

internal const val ROOT_PACKAGE = "com.jpmorgan.labs.micronaut.rest.example"

object Starter  {

    @JvmStatic
    fun main(vararg args: String) {

        startApplicationContext(args.toList())
    }

    fun startApplicationContext(args: Collection<String>): ApplicationContext = Micronaut.build().args(*args.toTypedArray()).packages(ROOT_PACKAGE).mainClass(Starter.javaClass).start()
}

/**
 * required by gradle to run application from command line with command
 * ./gradlew run
 */
fun main() {
    Starter.main()
}