package tech.zone84.examples.extensionfunctions.environment;

import mu.KotlinLogging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

class Environment {
    private val mongoDb = MongoDBContainer(DockerImageName.parse("mongo:5.0.5"));

    fun getMongoDbPort(): Int = mongoDb.getFirstMappedPort()

    fun startServices() {
        mongoDb.start()
        logger.info { "Started MongoDB service on mapped port " + getMongoDbPort() }
    }

    fun stopServices() {
        logger.info("Stopping all services...")
        mongoDb.stop();
        logger.info("Stopped all services")
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
