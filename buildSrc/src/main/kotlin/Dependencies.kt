import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

object Dependencies {

    fun log4j2Slf4jAdapter(version: String = Versions.Log4j.value) = log4j2("log4j-slf4j-impl", version)

    fun coroutinesSlf4j(version: String = Versions.Kotlin.Coroutines.value) = coroutines("slf4j", version)
    fun coroutinesCore(version: String = Versions.Kotlin.Coroutines.value) = coroutines("core", version)
    fun coroutinesJdk8(version: String = Versions.Kotlin.Coroutines.value) = coroutines("jdk8", version)
    fun coroutinesReactive(version: String = Versions.Kotlin.Coroutines.value) = coroutines("reactive", version)

    fun micronautBom(version: String = Versions.Micronaut.value): ModuleDependency = micronaut("micronaut-bom", version)
    fun micronautRuntime(version: String = Versions.Micronaut.value) = micronaut("micronaut-runtime", version)
    fun micronautHttpServerNetty(version: String = Versions.Micronaut.value) = micronaut("micronaut-http-server-netty", version)
    fun micronautManagement(version: String = Versions.Micronaut.value) = micronaut("micronaut-management", version)
    fun micronautHttp(version: String = Versions.Micronaut.value) = micronaut("micronaut-http", version)
    fun micronautHttpServer(version: String = Versions.Micronaut.value) = micronaut("micronaut-http-server", version)
    fun micronautInject(version: String = Versions.Micronaut.value) = micronaut("micronaut-inject-java", version)
    fun micronautTestJunit5(version: String = Versions.Micronaut.Test.value) = dependency("io.micronaut.test","micronaut-test-junit5", version)

    fun micronautKotlinRuntime(version: String = Versions.Micronaut.KotlinRuntime.value) = dependency("io.micronaut.kotlin", "micronaut-kotlin-runtime", version)
    fun micronautMicrometerCore(version: String = Versions.Micronaut.MicrometerMicrometer.value) = micronautMicrometer("micronaut-micrometer-core", version)
    fun micronautMicrometerPrometheusRegistry(version: String = Versions.Micronaut.MicrometerMicrometer.value) = micronautMicrometer("micronaut-micrometer-registry-prometheus", version)

    fun ktorClientApache(version: String = Versions.Ktor.value) = ktor("ktor-client-apache", version)

    fun json(version: String = Versions.Json.value) = dependency("org.json", "json", version)
    fun everitJsonSchema(version: String = Versions.EveritJsonSchema.value) = dependency("com.github.everit-org.json-schema", "org.everit.json.schema", version)

    fun javaAnnotation(version: String = Versions.Javax.Annotation.value)  = dependency("javax.annotation", "javax.annotation-api", version)
    fun guava(version: String = Versions.Guava.value) = dependency("com.google.guava", "guava", version)
    fun apacheCommons(version: String = Versions.ApacheCommons.value) = dependency("org.apache.commons","commons-lang3",version)
    fun ulid(version: String = Versions.Ulid.value) = dependency("de.huxhorn.sulky", "de.huxhorn.sulky.ulid", version)

    fun avro(version: String = Versions.Avro.value) = dependency("org.apache.avro" ,"avro", version)
    fun commonsCodec(version: String = Versions.ApacheCommons.Codec.value): ModuleDependency = DefaultExternalModuleDependency("commons-codec", "commons-codec", version)

    fun kotlinLogging(version: String = Versions.KotlinLogging.value) = dependency("io.github.microutils", "kotlin-logging-jvm", version)

    /* test */
    fun assertk(version: String = Versions.AssertK.value) = dependency("com.willowtreeapps.assertk", "assertk", version)
    fun mockk(version: String = Versions.Mockk.value) = dependency("io.mockk", "mockk", version)
    fun junitJupiterApi(version: String = Versions.JUnit.Jupiter.value) = junitJupiter("junit-jupiter-api", version)
    fun junitJupiterEngine(version: String = Versions.JUnit.Jupiter.value) = junitJupiter("junit-jupiter-engine", version)
}

private fun log4j2(name: String, version: String) = dependency("org.apache.logging.log4j", name, version)
private fun coroutines(name: String, version: String) = kotlinx("kotlinx-coroutines-$name", version)
private fun junitJupiter(name: String, version: String) = dependency("org.junit.jupiter", name, version)
private fun micronaut(name: String, version: String) = dependency("io.micronaut", name, version)
private fun micronautMicrometer(name: String, version: String) = dependency("io.micronaut.micrometer", name, version)
private fun ktor(name: String, version: String) = dependency("io.ktor", name, version)
private fun kotlinx(name: String, version: String) = dependency("org.jetbrains.kotlinx", name, version)
private fun dependency(group: String, name: String, version: String): ModuleDependency = DefaultExternalModuleDependency(group, name, version)