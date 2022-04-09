## Kotlin extension functions

This is an example project for [How to abuse Kotlin extension functions](https://zone84.tech/programming/how-to-abuse-kotlin-extension-functions/) article
published at [zone84.tech](https://zone84.tech). Note that the example provided here is actually a positive example :).
The example project also uses the solution from [Efficient test startup: JUnit + Micronaut + Testcontainers](https://zone84.tech/programming/efficient-test-startup-junit-micronaut-testcontainers/)
article, adapted to Kotlin.

You can download it and fully explore the presented solution.

## Overview

The project is a simple web service written in Micronaut for managing website articles. It contains two endpoints
`POST /articles/queried` for querying articles by category, and `POST /articles` to insert a new article.

The prominent feature are object-oriented mappers that map JSON DTO-s into business logic. Kotlin extension functions
are also used, but not for implementing the actual mappers. Instead, they bind the mapping interfaces with Reactor
API-s. This is the valid use case for them, as presented in the article.

There is a small integration test `DemoTest.kt` that runs the web service and makes a couple of HTTP requests to
the provided endpoints. It spawns MongoDB as a Docker container. You can use it for testing.

A careful eye can notice that the domain entity `Article` contains MongoDB repository annotations. This is not
what you should do in regular business applications. You can treat it as an exercise: try to write a proper
entity that represents a MongoDB document, and use `InputMapper<I, D>` and `OutputMapper<D, O>` to implement
mapping between the domain and the database world.

## Summary

Tech stack:

- Kotlin
- Micronaut 3.4
- Project Reactor
- MongoDB
- Docker (needs to be installed separately)

Author: Tomasz Jędrzejewski

License: MIT License
