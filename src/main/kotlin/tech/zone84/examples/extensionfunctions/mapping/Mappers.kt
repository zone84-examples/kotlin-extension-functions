package tech.zone84.examples.extensionfunctions.mapping

/**
 * General-purpose interface to map incoming DTO-s into domain (business) objects.
 */
interface InputMapper<I, D> {
    fun toDomain(input: I): D
}

/**
 * General-purpose interface to map domain (business) objects into output DTO-s.
 */
interface OutputMapper<D, O> {
    fun fromDomain(output: D): O
}
