# Micronaut REST example

[comment]: <> (I have been working at Chase for about two months in the Dynamo Cloud team)
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


### Setup
- Install JDK 17
- Micronaut requires Annotation Processing, see [ideaSetup](https://docs.micronaut.io/latest/guide/#ideaSetup)
- Kotlin requires Kapt for Annotation Processing, see [kaptintellij](https://docs.micronaut.io/latest/guide/#kaptintellij)

### Useful Links
[comment]: <TODO> (Look up the correct documentation link)

- [Micronaut Documentation](https://docs.micronaut.io/latest/guide/)
- [Micronaut Guides](https://micronaut.io/guides/)
- [Micronaut Launch](https://micronaut.io/launch/)