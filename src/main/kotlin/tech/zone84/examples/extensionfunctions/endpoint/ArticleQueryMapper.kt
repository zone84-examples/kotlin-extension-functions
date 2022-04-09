package tech.zone84.examples.extensionfunctions.endpoint

import tech.zone84.examples.extensionfunctions.domain.Article
import tech.zone84.examples.extensionfunctions.domain.ArticleQuery
import tech.zone84.examples.extensionfunctions.mapping.InputMapper
import tech.zone84.examples.extensionfunctions.mapping.OutputMapper

/**
 * An example mapper for `/articles/queried` endpoint where we group both mapping the query JSON into the query
 * object, and the result into the response format. Thanks to this, the REST API structure is separated from the
 * business domain and can be expanded independently.
 *
 * Because the mappers implement common interfaces, we can write utilities that work with every mapper. You
 * can find an example for this in `ReactorMapperBindings.kt` file.
 */
object ArticleQueryMapper : InputMapper<ArticleQueryJson, ArticleQuery>, OutputMapper<Pair<ArticleQuery, List<Article>>, ArticleQueryResultJson> {
    override fun toDomain(input: ArticleQueryJson) = ArticleQuery(category = input.category)
    override fun fromDomain(output: Pair<ArticleQuery, List<Article>>) = ArticleQueryResultJson(
        query = ArticleQueryJson(category = output.first.category),
        results = output.second.map { article ->
            ArticleJson(
                id = article.id ?: "",
                title = article.title,
                content = article.content,
                category = article.category
            )
        }
    )
}

data class ArticleQueryJson(
    val category: String
)

data class ArticleQueryResultJson(
    val query: ArticleQueryJson,
    val results: List<ArticleJson>
)

data class ArticleJson(
    val id: String,
    val title: String,
    val content: String,
    val category: String
)
