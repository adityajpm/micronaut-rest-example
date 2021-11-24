# Micronaut REST example

The project demonstrates how a REST service can be built using micronaut

### Repo
- git clone https://github.com/adityajpm/micronaut-rest-example.git    

### Micronaut
Similar to Spring Boot but has faster boot time, as most of the framework logic is computed at compilation time. Micronaut uses byte code enrichment during compilation time. This eliminates need for Runtime Reflection, Runtime Proxies and Dynamic Class Loading.


### Setup
- Install JDK 17
- Micronaut requires Annotation Processing, see [ideaSetup](https://docs.micronaut.io/latest/guide/#ideaSetup) 
- Kotlin requires Kapt for Annotation Processing, see [kaptintellij](https://docs.micronaut.io/latest/guide/#kaptintellij)

### Useful Links

- [Micronaut Documentation](https://docs.micronaut.io/latest/guide/) 
- [Micronaut Guides](https://micronaut.io/guides/)
- [Micronaut Launch](https://micronaut.io/launch/)

[comment]: New Page

### Project Structure
- Overview  of project -  Hexagonal Architecture
- 
Structure / Hexagonal / Domain / Adapters / Application

![Diagram](doc/HexagonalArchitecture.png)

for more info [Hexagonal Architecture article](https://medium.com/ssense-tech/hexagonal-architecture-there-are-always-two-sides-to-every-story-bc0780ed7d9c)


[comment]: New Page

###Starter.kt
- Entry Point -> Create Application Context using builder

###Application.yml
- application.yml properties can be overridden by system properties

###TransferEndPoint

  - Api.Endpoints.Transfer
  - Json Schema Validation used instead of Bean Validation Annotations (schema can be shared with frontend clients)
  - @Controller
  - @Context

###TransferEndPointContractTest
  - Testing - Bean Replacing,  EmbeddedServer , Ktor Client, Mocking

###CORSEndpointTest
   - Using Options Method to confirm Http Server configured correctly

###ManagementEndpointsTest
   - /actuator/health/readiness  
   - /actuator/health/liveness 

###MetricsEndpointTest
   - Prometheus Micrometer
   - /actuator/prometheus


