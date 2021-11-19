plugins {
    kotlin("plugin.allopen")
}

dependencies {
    with(Dependencies) {
        api(project(":application"))
        implementation(project(":commons-json-schema-utils"))
        implementation(project(":commons-logging"))

        implementation(micronautMicrometerCore())
        implementation(micronautMicrometerPrometheusRegistry())
        implementation(guava())
        implementation(swaggerAnnotations())
        micronautOpenApi().also(::implementation).also(::kapt)

        runtimeOnly(micronautHttpServerNetty())

        testImplementation(project(":configuration"))
        testImplementation(project(":commons-json-schema-test"))
        testImplementation(javaAnnotation())
        testImplementation(micronautTestJunit5())
        testApi(ktorClientApache())

        micronautInject().also(::implementation).also(::kapt).also(::kaptTest)
        micronautHttp().also(::implementation).also(::kapt).also(::kaptTest)
        micronautHttpServer().also(::implementation).also(::kapt).also(::kaptTest)
        micronautManagement().also(::implementation).also(::kapt).also(::kaptTest)
        platform(micronautBom()).also(::implementation).also(::kapt).also(::kaptTest)
    }
}

allOpen {
    annotation("io.micronaut.aop.Around")
    annotation("io.micronaut.test.annotation.MockBean")
    annotation("io.micronaut.context.annotation.Context")
    annotation("io.micronaut.context.annotation.Singleton")
}

//kapt {
//    arguments {
//        arg("micronaut.openapi.views.spec", "redoc.enabled=true,rapidoc.enabled=true,swagger-ui.enabled=true,swagger-ui.theme=flattop")
//        arg("micronaut.openapi.config.file", "api/Api.yml")
//    }
//}

kapt {
    arguments {
        arg("micronaut.openapi.views.spec", "swagger-ui.enabled=true,swagger-ui.theme=flattop")
    }
}