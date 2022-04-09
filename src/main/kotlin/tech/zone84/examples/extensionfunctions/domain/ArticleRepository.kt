package tech.zone84.examples.extensionfunctions.domain

import reactor.core.publisher.Mono

interface ArticleRepository {
    fun queryArticles(query: ArticleQuery): Mono<List<Article>>
    fun insert(article: Article): Mono<String>
}
