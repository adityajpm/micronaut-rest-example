rootProject.name = "micronaut-rest-example"

module("adapters", "driving", "web")
module("configuration")
module("starter")
module("application")
module("domain")
module("commons", "test")
module("commons", "logging")
module("commons", "domain")
module("commons", "json", "schema", "utils")
module("commons", "json", "schema", "test")


fun module(vararg pathSegments: String) {
    val projectName = pathSegments.last()
    val path = pathSegments.dropLast(1)
    val group = path.joinToString(separator = "-")
    val directory = path.joinToString(separator = "/", prefix = "./")

    include("${if (group.isEmpty()) "" else "$group-"}$projectName")
    project(":${if (group.isEmpty()) "" else "$group-"}$projectName").projectDir = mkdir("$directory/$projectName")
}