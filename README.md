# Micronaut REST example

The project demonstrates how a REST service can be built using micronaut

### Repo
- git clone https://github.com/adityajpm/micronaut-rest-example.git    

### Micronaut
Similar to Spring Boot but has faster boot time, as most of the framework logic is computed at compilation time. Micronaut uses byte code enrichment during compilation time. This eliminates need for Runtime Reflection, Runtime Proxies and Dynamic Class Loading.

### Setup
- Install JDK 17
- Micronaut requires Annotation Processing to setup see [ideaSetup](https://docs.micronaut.io/latest/guide/#ideaSetup) 
- Kotlin requires Kapt for Annotation Processing to setup see   [kaptintellij](https://docs.micronaut.io/latest/guide/#kaptintellij)

### Useful Links

- [Building Graal Images](https://guides.micronaut.io/latest/micronaut-creating-first-graal-app.html)
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