dependencies{
    with(Dependencies){
        api(project(":domain"))

        api(project(":commons-domain"))
        implementation(project(":commons-logging"))

        micronautInject().also(::implementation).also(::kapt).also(::kaptTest)
    }
}