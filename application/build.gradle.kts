dependencies{
    with(Dependencies){
        api(project(":domain"))
        api(project(":commons-domain"))

        micronautInject().also(::implementation).also(::kapt).also(::kaptTest)
    }
}