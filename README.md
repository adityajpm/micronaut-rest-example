# Micronaut REST example

[comment]: <TODO> (TODO Split into script and presentation)
[comment]: <> (I have been wokring at Chase for about two months in the Dynamo Cloud team)
[comment]: <> (I have used Micronaut for about a year in production and is being currently used in Dynamo Cloud team)
[comment]: <> (The example project has been written in kotlin  , but all the mironaut setup, configuration  and annotations are identical when using Java)
[comment]: <> (The feature of Mirconaut are to many to walk through in this presentation)
[comment]: <> (This presentation will focus a few features when building a rest micro-service )

The project demonstrates how a REST service can be built using micronaut
  
[comment]: <> (Invesigate how to reference classes from read me)

### Repo
- git clone https://github.com/adityajpm/micronaut-rest-example.git    

### Micronaut
Similar to Spring Boot but has faster boot time, as most of the framework logic is computed at compilation time. Micronaut uses byte code enrichment during compilation time. This eliminates need for Runtime Reflection, Runtime Proxies and Dynamic Class Loading.

[comment]: <> (TODO History about micronaut)

[comment]: <> (Like Lombok Micronaut uses Annotation Processing to apply byte code enhancement)

### Setup
- Install JDK 17
- Micronaut requires Annotation Processing, see [ideaSetup](https://docs.micronaut.io/latest/guide/#ideaSetup) 
- Kotlin requires Kapt for Annotation Processing, see [kaptintellij](https://docs.micronaut.io/latest/guide/#kaptintellij)

### Useful Links
[comment]: <TODO> (Look up the correct documentation link)

- [Micronaut Documentation](https://docs.micronaut.io/latest/guide/) 
- [Micronaut Guides](https://micronaut.io/guides/)
- [Micronaut Launch](https://micronaut.io/launch/)

### Request Test Example
POST http://localhost:8080/
Content-Type: application/json

{
"userId":"01FM4JE6DVQ9MP08YREJRW6AYZ",
"amount":10,
"accountFrom":"12345678",
"accountTo":"12345679"
}

### Demo notes

- Context - Simple Rest App written in Micronaut and Kotlin
- Overview  of project 
  - Starter Module

[comment]: <> (TODO What is the packages for)
[comment]: <> (TODO How to add system properties)
  - System Properties

[comment]: <TODO> (How to add system properties)
  - application.yml - can be overridden by system properties

[comment]: <> (Write a few notes on Hexagonal Architecture, Find a diagram )
  - Structure / Hexagonal / Domain / Adapters / Application 

[comment]: <> (Transfer interface outlines the exposed web methods, @POST annotation )
  - Api

[comment]: <> (@Controller works in the same way as in Spring )
[comment]: <> (@Context tells Micronaut this bean is eagerly instanstiated)
[comment]: <> (Using Schema validation as apposed Bean Validation, that a preference)
  - TransferEndPoint 

[comment]: <TODO> (Controller annotation Write a few notes on Hexagonal Architecture, Find a )
  - 
  - Json Schema Validation used instead of Bean Validation Annotations, schema can be shared with frontend team
  - Testing - Bean Replacing,  EmbeddedServer , Ktor Client, Mocking
  - Demo
  - Useful links

[comment]: <> (Cors Setup)
[comment]: <> (Management)
[comment]: <> (Metrics)
[comment]: <> (Performance Test)

[comment]: <> (other notable features - Kubernetes Config Map Configuration, so Guides cloud )
[comment]: <> ()

