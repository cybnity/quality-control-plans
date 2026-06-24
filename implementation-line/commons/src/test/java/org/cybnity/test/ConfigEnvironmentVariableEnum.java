package org.cybnity.test;

import org.cybnity.framework.IReadableConfiguration;

/**
 * Test enumeration representing a configuration variable defined by a library built by a CYBNITY project.
 * This class allow to validate the automated instantiation of enum from properties file.
 */
public enum ConfigEnvironmentVariableEnum implements IReadableConfiguration {
    X("X"),
    Y("Y");

    /**
     * Name of this environment variable currently hosted by the system environment.
     */
    private final String name;

    /**
     * Default constructor of a configuration variable that is readable from the
     * system environment variables set.
     *
     * @param aName Mandatory name of the environment variable that is readable from
     *              the current system environment (e.g defined by the runtime
     *              container or operating system).
     * @throws IllegalArgumentException When mandatory parameter is not defined.
     */
    ConfigEnvironmentVariableEnum(String aName) throws IllegalArgumentException {
        if (aName == null || "".equalsIgnoreCase(aName))
            throw new IllegalArgumentException("The name of this variable shall be defined!");
        this.name = aName;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
