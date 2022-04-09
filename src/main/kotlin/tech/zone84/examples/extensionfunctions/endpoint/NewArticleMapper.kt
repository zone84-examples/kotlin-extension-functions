package tech.zone84.examples.extensionfunctions.endpoint

import tech.zone84.examples.extensionfunctions.domain.Article
import tech.zone84.examples.extensionfunctions.mapping.InputMapper

/**
 * An example mapper for inserting new articles. Here, we implement only the input mapping, because this
 * endpoint produces no response.
 *
 * Because the mappers implement common interfaces, we can write utilities that work with every mapper. You
 * can find an example for this in `ReactorMapperBindings.kt` file.
 */
object NewArticleMapper : InputMapper<NewArticleJson, Article> {
    override fun toDomain(input: NewArticleJson) = Article(
        id = null,
        title = input.title,
        category = input.category,
        content = input.content
    )
}

data class NewArticleJson(
    val title: String,
    val content: String,
    val category: String
)
