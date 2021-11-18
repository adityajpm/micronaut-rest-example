dependencies(){
    with(Dependencies){
        api(kotlinLogging())
        api(coroutinesSlf4j())

//        runtimeOnly(simpleSlf4jAdapter())
        runtimeOnly(log4j2Slf4jAdapter())
    }
}