package org.cybnity.test;

import org.cybnity.framework.IContext;
import org.cybnity.test.commons.ContextualizedTest;

/**
 * Example of test case including environment variables read from a properties file.
 */
public class ContextualizedSample extends ContextualizedTest {

    /**
     * Default constructor with automatic environment variables load as context properties from a file path.
     *
     * @param environmentVariablesPropertySourceFilePath File path where properties can be read.
     * @throws Exception When problem during the read of file.
     */
    protected ContextualizedSample(String environmentVariablesPropertySourceFilePath) throws Exception {
        super(environmentVariablesPropertySourceFilePath);
    }

    /**
     * Get external access to test context for quality control.
     *
     * @return A context instance including environment variable names and values.
     */
    public IContext getContext() {
        return super.getContext();
    }
}
