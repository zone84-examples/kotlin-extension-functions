package tech.zone84.examples.extensionfunctions.database

import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import tech.zone84.examples.extensionfunctions.domain.Article
import tech.zone84.examples.extensionfunctions.domain.ArticleQuery
import tech.zone84.examples.extensionfunctions.domain.ArticleRepository

@Singleton
class DatabaseArticleRepository(private val mongoRepository: MongoArticleRepository) : ArticleRepository {
    override fun queryArticles(query: ArticleQuery): Mono<List<Article>> {
        return mongoRepository.findByCategory(query.category)
            .collectList()
    }

    override fun insert(article: Article): Mono<String> {
        return mongoRepository
            .save(article)
            .map { it.id }
    }
}
