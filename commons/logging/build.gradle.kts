dependencies(){
    with(Dependencies){
        api(kotlinLogging())
        api(coroutinesSlf4j())

//        runtimeOnly(simpleSlf4jAdapter())
        runtimeOnly(simpleSlf4jAdapter())
    }
}