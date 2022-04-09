package tech.zone84.examples.extensionfunctions

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import tech.zone84.examples.extensionfunctions.endpoint.ArticleJson
import tech.zone84.examples.extensionfunctions.endpoint.ArticleQueryResultJson

class ArticleAbility(private val client: HttpClient) {
    fun insertArticle(title: String, content: String, category: String) {
        val query = "{\"title\": \"$title\", \"content\": \"$content\", \"category\": \"$category\"}"
        val request: HttpRequest<String> = HttpRequest.POST("/articles", query)
        val response: HttpResponse<Nothing> = client.toBlocking().exchange(request)

        assertThat(response.code()).isEqualTo(HttpStatus.NO_CONTENT.code)
    }

    fun queryArticles(category: String): List<ArticleJson> {
        val query = "{\"category\": \"$category\"}"
        val request: HttpRequest<String> = HttpRequest.POST("/articles/queried", query)
        val response = client.toBlocking().exchange(request, Argument.of(ArticleQueryResultJson::class.java))

        assertThat(response.code()).isEqualTo(HttpStatus.OK.code)
        val body = response.body()
        assertThat(body).isNotNull

        logger.info { "returned response for category '$category': $body" }
        return body.results
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
