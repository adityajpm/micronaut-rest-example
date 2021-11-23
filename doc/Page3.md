###Starter.kt 
- Entry Point -> Create Application Context using builder see Starter.kt
- application.yml - can be overridden by system properties


[comment]: <> (Transfer interface outlines the exposed web methods, @POST annotation )
###Api

[comment]: <> (@Controller works in the same way as in Spring )
[comment]: <> (@Context tells Micronaut this bean is eagerly instanstiated)
[comment]: <> (Using Schema validation as apposed Bean Validation, that a preference)

###TransferEndPoint

[comment]: <> (Controller annotation Write a few notes on Hexagonal Architecture, Find a diagram)

- Json Schema Validation used instead of Bean Validation Annotations, schema can be shared with frontend team

###Testing

- Testing - Bean Replacing,  EmbeddedServer , Ktor Client, Mocking

### Management EndPoints

- Readiness
- Liveness

### Cors

- Test setup using Options request

### Metrics

- Prometheus Micrometer

[comment]: <> (Performance Test)

[comment]: <> (other notable features - Kubernetes Config Map Configuration, so Guides cloud )
