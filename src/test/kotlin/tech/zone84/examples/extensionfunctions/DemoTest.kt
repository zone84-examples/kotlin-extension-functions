package tech.zone84.examples.extensionfunctions

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DemoTest(@Client("/") private val client: HttpClient) {
    val ability = ArticleAbility(client)

    @Test
    @Order(1)
    fun `should put several articles into the database`() {
        ability.insertArticle(
            title = "How to abuse Kotlin extension functions",
            content = "This is some content...",
            category = "programming"
        )
        ability.insertArticle(
            title = "Architecture Decision Records: 3 keys to success",
            content = "This is some content...",
            category = "architecture"
        )
        ability.insertArticle(
            title = "Creating batches with Project Reactor",
            content = "This is some content...",
            category = "programming"
        )

    }

    @Test
    @Order(2)
    fun `should fetch the articles using a filter`() {
        // given
        val queriedCategory = "programming"

        // when
        val results = ability.queryArticles(queriedCategory)

        // then
        for (article in results) {
            assertThat(article.category)
                .describedAs("article '${article.title}'")
                .isEqualTo("programming")
        }
    }
}
