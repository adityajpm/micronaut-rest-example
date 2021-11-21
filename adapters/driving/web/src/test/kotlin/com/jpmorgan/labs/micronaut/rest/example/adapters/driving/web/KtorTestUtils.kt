package com.jpmorgan.labs.micronaut.rest.example.adapters.driving.web

import io.micronaut.runtime.server.EmbeddedServer

internal fun EmbeddedServer.url(path : String) = "http://${host}:${port}/$path"