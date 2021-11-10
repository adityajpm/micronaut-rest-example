dependencies(){
    with(Dependencies){
        api(kotlinLogging())
        api(coroutinesSlf4j())

        runtimeOnly(log4j2Slf4jAdapter())
    }
}