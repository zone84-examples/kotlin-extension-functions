package tech.zone84.examples.extensionfunctions.mapping

open class InputMappingException(message: String, cause: Exception): RuntimeException(message, cause)
open class OutputMappingException(message: String, cause: Exception): RuntimeException(message, cause)
