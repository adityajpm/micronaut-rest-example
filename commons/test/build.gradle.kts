dependencies(){
    with(Dependencies){
        api(kotlin("test"))
        api(junitJupiterApi())
        api(assertk())
        api(mockk())

        testImplementation(junitJupiterEngine())
    }
}