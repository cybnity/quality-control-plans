package org.cybnity.test.commons;

import org.cybnity.framework.Context;
import org.cybnity.framework.IContext;
import org.testng.log4testng.Logger;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.resource.PropertySource;

/**
 * Automated configuration of test execution context.
 * Each test requiring a configured context including test environment variables shall extend this class.
 * EmbeddedRedisExtension.class for Redis 6.0.5 used by default
 **/
public class ContextualizedTest {

    /**
     * Current started process' environment variables.
     */
    private EnvironmentVariables environmentVariables;

    /**
     * Test context including environment variables.
     */
    private IContext context;

    /**
     * Logger about this test execution.
     */
    protected Logger logger;

    /**
     * Default constructor with automatic empty context creation.
     * Default empty context is defined by default.
     */
    public ContextualizedTest() {
        setupLogger();
        try {
            setupEnvironmentVariables(null);
        } catch (Exception e) {
            logger.error("Error setting up environment", e);
        }
    }

    /**
     * Default constructor with automatic environment variables load as context properties from a file path.
     *
     * @param environmentVariablesPropertySourceFilePath File path where properties can be read.
     * @throws Exception When problem during the read of file.
     */
    protected ContextualizedTest(String environmentVariablesPropertySourceFilePath) throws Exception {
        setupLogger();
        setupEnvironmentVariables(environmentVariablesPropertySourceFilePath);
    }

    /**
     * Read environment variables as context properties from a file path.
     * Setup or refresh the context according to the read (or empty set) environment variables.
     *
     * @param environmentVariablesPropertySourceFilePath Optional file path where properties are defined.
     * @throws Exception When problem during the read of file or during System variables setup.
     */
    protected void setupEnvironmentVariables(String environmentVariablesPropertySourceFilePath) throws Exception {
        if (environmentVariablesPropertySourceFilePath != null && !environmentVariablesPropertySourceFilePath.isEmpty()) {
            // Load and defined environment variable from a file path
            environmentVariables = new EnvironmentVariables().set(
                    PropertySource.fromFile(environmentVariablesPropertySourceFilePath));
        } else {
            environmentVariables = new EnvironmentVariables(); // Empty container
        }
        // Initialize the context instance based on properties file values
        setupContext();
    }

    /**
     * Initialize the logger regarding this instance class type
     */
    private void setupLogger() {
        this.logger = Logger.getLogger(this.getClass());
    }

    /**
     * Get logger about this instance.
     *
     * @return A logger instance.
     */
    protected Logger logger() {
        return logger;
    }

    /**
     * Initialize a context instance optionally including defined environment variables.
     */
    private void setupContext() {
        // Build reusable context
        if (this.context == null)
            this.context = new Context();

        getEnvironmentVariables().getVariables().forEach((key, value) -> {
            try {
                this.context.addResource(value, key, true /* force refresh of potential existing previous value */);
            } catch (Exception e) {
                // Error of value into the properties file
                logger.error(e.getMessage());
            }
        });
    }

    /**
     * Get the current environment variables.
     *
     * @return Variables set defined from property source file, or empty container.
     */
    protected EnvironmentVariables getEnvironmentVariables() {
        return environmentVariables;
    }

    /**
     * Get test context.
     *
     * @return A context instance including environment variable names and values.
     */
    protected IContext getContext() {
        return this.context;
    }

    /**
     * To use before test execution.
     *
     * @throws Exception When System-Stub activation problem.
     */
    protected void enableEnvSetup() throws Exception {
        // start controlling the environment
        environmentVariables.setup();
    }

    /**
     * To use after test execution.
     *
     * @throws Exception When System-Stub disabling problem.
     */
    protected void disableEnvSetup() throws Exception {
        environmentVariables.teardown();
    }

}
