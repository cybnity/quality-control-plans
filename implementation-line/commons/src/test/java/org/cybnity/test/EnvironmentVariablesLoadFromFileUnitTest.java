package org.cybnity.test;

import org.cybnity.framework.IContext;
import org.cybnity.test.commons.ContextualizedTest;
import org.junit.jupiter.api.Test;
import org.testng.AssertJUnit;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Test read and instantiation of Enum instance from a properties file.
 */
public class EnvironmentVariablesLoadFromFileUnitTest extends ContextualizedTest {

    private static final String proFilePath = "target/test-classes/env.properties";

    public EnvironmentVariablesLoadFromFileUnitTest() throws Exception {
        super(proFilePath);
    }

    /**
     * Check that a valid properties file is read without problem, and is processed for environment variables extractions.
     *
     * @throws Exception When file is not found.
     */
    @Test
    public void givenValidPropertiesFile_whenContextualizedTestCreation_thenSuccessDefinedContext() throws Exception {
        // Simulate a path to valid properties file including defined environment variables identities and values
        Properties props = new Properties();
        props.load(new FileInputStream(proFilePath));
        // Check that test file is found and readable
        AssertJUnit.assertNotNull(props.getProperty("Y"));

        // Create an instance of contextualized test expected to be automatically set from environment variables
        ContextualizedSample sample = new ContextualizedSample(proFilePath);
        // Check feed of context with enumerations
        IContext feedCtx = sample.getContext();
        AssertJUnit.assertNotNull(feedCtx);
        // Test search value of a configuration Enum from context
        String value = (String) feedCtx.get(ConfigEnvironmentVariableEnum.Y.getName());
        AssertJUnit.assertEquals("value2", value);
        // Check if all ConfigEnvironmentVariableEnum are have been initialized into the context
        AssertJUnit.assertEquals("value2", feedCtx.get(ConfigEnvironmentVariableEnum.Y.getName()));
        AssertJUnit.assertEquals("value1", feedCtx.get(ConfigEnvironmentVariableEnum.X.getName()));
    }

    /**
     * Unit test that verify if defined variable by properties files, are found during the test execution.
     *
     * @throws Exception When problem of System-Stub usage.
     */
    @Test
    public void givenLoadedPropertiesFile_whenTestRunning_SystemVariableValueAvailable() throws Exception {
        // Verify than environment variable can be read from System (thanks to System-stub
        getEnvironmentVariables().execute(() -> {
            String currentThreadEnvSystemVariable = System.getenv(ConfigEnvironmentVariableEnum.Y.getName());
            AssertJUnit.assertEquals("value2", currentThreadEnvSystemVariable);
        });
    }

}
