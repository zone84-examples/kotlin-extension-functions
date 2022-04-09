package tech.zone84.examples.extensionfunctions.environment;

import io.micronaut.context.env.ActiveEnvironment;
import io.micronaut.context.env.PropertySource;
import io.micronaut.context.env.PropertySourceLoader;
import io.micronaut.core.io.ResourceLoader;
import mu.KotlinLogging
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

class EnvironmentStartupListener : TestExecutionListener, PropertySourceLoader {
    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        logger.info { "Starting the environment..." }
        environment = Environment()
        environment.startServices()
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        logger.info { "Stopping the environment..." }
        environment.stopServices()
    }

    override fun load(resourceName: String, resourceLoader: ResourceLoader): Optional<PropertySource> {
        if (resourceName.equals("application")) {
            return Optional.of(PropertySource.of(mapOf(
                    "mongodb.uri" to "mongodb://localhost:${environment.getMongoDbPort()}/example"
                )))
        }
        return Optional.empty()
    }

    override fun read(name: String?, input: InputStream?): MutableMap<String, Any> {
        return mutableMapOf()
    }

    override fun loadEnv(
        resourceName: String?,
        resourceLoader: ResourceLoader?,
        activeEnvironment: ActiveEnvironment?
    ) = Optional.empty<PropertySource>()

    companion object {
        @Volatile
        private lateinit var environment: Environment
        private val logger = KotlinLogging.logger { }
    }
}
