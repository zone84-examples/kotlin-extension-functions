package tech.zone84.examples.extensionfunctions.endpoint

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Mono
import tech.zone84.examples.extensionfunctions.domain.ArticleRepository
import tech.zone84.examples.extensionfunctions.mapping.fromDomain
import tech.zone84.examples.extensionfunctions.mapping.toDomain

@Controller("/articles")
class ArticleController(private val repository: ArticleRepository) {
    @Post("/queried")
    fun query(@Body flow: Mono<ArticleQueryJson>): Mono<ArticleQueryResultJson> {
        return flow
            .toDomain(ArticleQueryMapper)
            .flatMap { query ->
                repository.queryArticles(query)
                    .map { results -> Pair(query, results) }
            }
            .fromDomain(ArticleQueryMapper)
    }

    @Post
    fun insert(@Body flow: Mono<NewArticleJson>): Mono<HttpResponse<*>> {
        return flow
            .toDomain(NewArticleMapper)
            .flatMap { repository.insert(it) }
            .map { HttpResponse.noContent<Void>() }
    }
}
