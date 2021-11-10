package com.jpmorgan.labs.jvm.commons.json.schema.utils

import com.google.common.io.Resources
import org.everit.json.schema.Schema
import org.everit.json.schema.loader.SchemaClient
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener
import java.io.InputStream

fun jsonSchemaAt(location: String, schemasFolder: String? = null): Schema = openAsStream(location).use {
    val definition = JSONObject(JSONTokener(it))
    SchemaLoader.builder().schemaClient(SchemaClient.classPathAwareClient()).apply { if (schemasFolder != null) resolutionScope("classpath://$schemasFolder") }.schemaJson(definition).build().load().build()
}

fun jsonSchemaUnderRootFolder(schemaPathFromRootFolder: String, rootFolderPath: String) = jsonSchemaAt("$rootFolderPath/$schemaPathFromRootFolder", "$rootFolderPath/")

@Suppress("UnstableApiUsage")
private fun openAsStream(resourceName: String): InputStream = Resources.getResource(resourceName).openStream()