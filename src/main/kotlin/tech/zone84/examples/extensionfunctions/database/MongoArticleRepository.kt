package tech.zone84.examples.extensionfunctions.database

import io.micronaut.data.annotation.Repository
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.reactive.ReactorCrudRepository
import reactor.core.publisher.Flux
import tech.zone84.examples.extensionfunctions.domain.Article

@Repository
@MongoRepository(databaseName = "example")
interface MongoArticleRepository : ReactorCrudRepository<Article, String> {
    fun findByCategory(category: String): Flux<Article>
}
