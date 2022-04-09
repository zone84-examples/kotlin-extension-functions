package tech.zone84.examples.extensionfunctions.domain

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

/**
 * Note that using repository annotations on a domain entity is a small shortcut just for the example purposes.
 * Normally, you should not do that to avoid compatibility issues between the domain logic and the database
 * schema.
 *
 * Mappers can be also very useful for mapping domain entities into database entities. Try it out as an exercise!
 */
@MappedEntity
data class Article(
    @field:Id
    @field:GeneratedValue
    val id: String?,
    val title: String,
    val content: String,
    val category: String
)
