package tech.zone84.examples.extensionfunctions.endpoint

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.Produces
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import mu.KotlinLogging
import tech.zone84.examples.extensionfunctions.mapping.InputMappingException

@Produces
@Singleton
@Requires(classes = [InputMappingException::class, ExceptionHandler::class])
class InputMappingExceptionHandler : ExceptionHandler<InputMappingException, HttpResponse<JsonError>> {
    override fun handle(request: HttpRequest<*>, exception: InputMappingException): HttpResponse<JsonError> {
        logger.warn(exception) { "A request to ${request.path} failed to map the input data: ${exception.message} " }
        return unprocessableEntity<JsonError>()
            .body(JsonError("Unable to process the input data"))
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
