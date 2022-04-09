package tech.zone84.examples.extensionfunctions.mapping

import reactor.core.publisher.Mono

/**
 * This is an example how a predefined mapper interface can help enforcing some common conventions. This is
 * a valid use case for extension functions: we extend a third-party API (Reactor) to work nicely with
 * our code.
 *
 * Because all mappers have a common interface, we can easily enforce common handling of mapping exceptions,
 * and wrap them into [InputMappingException], which is then returned as 422 Unprocessable Entity. Every
 * controller that uses this extension function, would get this logic for free.
 */
fun <I, D> Mono<I>.toDomain(mapper: InputMapper<I, D>): Mono<D> {
    return map {
        try {
            mapper.toDomain(it)
        } catch (exception: RuntimeException) {
            throw InputMappingException("Unable to parse the input data.", exception)
        }
    }
}

fun <D, O> Mono<D>.fromDomain(mapper: OutputMapper<D, O>): Mono<O> {
    return map {
        try {
            mapper.fromDomain(it)
        } catch (exception: RuntimeException) {
            throw OutputMappingException("Unable to parse the results.", exception)
        }
    }
}
