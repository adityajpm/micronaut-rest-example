###Starter.kt
- Entry Point -> Create Application Context using builder

[comment]: <> (passing Application Arguments, Packages to scan for Beans and main class )
[comment]: <> (show build.gradle.kts showing web adapter dependency )

###Application.yml
- application.yml properties can be overridden by system properties

###TransferEndPoint

[comment]: <> ( interface outlines the exposed web methods, @POST annotation )
- Api.Endpoints.Transfer
- Json Schema Validation used instead of Bean Validation Annotations (schema can be shared with frontend clients)
- @Controller
- @Context

 [comment]: <> (@Controller works in the same way as in Spring )
 [comment]: <> (@Context tells Micronaut this bean is eagerly instanstiated)
 [comment]: <> (Using Schema validation as apposed Bean Validation, that a preference)

 [comment]: <> (Controller annotation Write a few notes on Hexagonal Architecture, Find a diagram)

###TransferEndPointContractTest
 - @MockBean
 - @Replaces
 - Testing - Bean Replacing,  EmbeddedServer , Ktor Client, Mocking

###CORSEndpointTest
- Using Options Method to confirm Http Server configured correctly

###ManagementEndpointsTest
- /actuator/health/readiness
- /actuator/health/liveness

###MetricsEndpointTest
- Prometheus Micrometer
- /actuator/prometheus

[comment]: <> (other notable features - Kubernetes Config Map Configuration, so Guides cloud )
