plugins {
    id("io.micronaut.application")
}

dependencies{
    with(Dependencies){
        implementation(project(":adapters-driving-web"))

        implementation(micronautRuntime())
        implementation(micronautKotlinRuntime())
        implementation(project(":configuration"))
        implementation(project(":commons-logging"))
    }
}

application {
    mainClass.set("com.jpmorgan.labs.micronaut.rest.example.StarterKt")
}

micronaut {
    version.set(Versions.Micronaut.value)
    processing {
        incremental(true)
        annotations("com.jpmorgan.labs.micronaut.rest.example.*")
    }
}
