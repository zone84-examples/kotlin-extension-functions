package tech.zone84.examples.extensionfunctions

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("tech.zone84.examples.extensionfunctions")
		.start()
}

