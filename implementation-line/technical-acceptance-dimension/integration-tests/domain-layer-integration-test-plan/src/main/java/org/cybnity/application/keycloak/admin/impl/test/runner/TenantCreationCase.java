package org.cybnity.application.keycloak.admin.impl.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Tests plan about tenant creation feature into a Keycloak instance over the Keycloak Admin API
 * By default, glue code is also assumed to be in the same package.
 */
// See "Using plugins" chapter of https://cucumber.io/docs/cucumber/api/#using-plugins about available options configuration
@CucumberOptions(
        features = "src/main/resources/org/cybnity/application/keycloak/admin/impl/test/TenantCreation.feature",
        glue = {"org.cybnity.application.keycloak.admin.impl.test.definitions"} /* package path to step definition file */,
        tags = "@integrationTest" /* Tag value referencing test in feature file from this scenario */,
        plugin = {"message:target/cucumber-report.ndjson",
                "html:target/cucumber-tenant-creation-report.html", "pretty" /* 2 formatter plugins to use */},
        monochrome = true /* Console output from Cucumber in readable format*/)
public class TenantCreationCase extends AbstractTestNGCucumberTests {

    /**
     * Path name of environment variable definitions required during the test execution.
     */
    public static String ENV_PROPERTY_FILEPATH = "target/test-classes/env.properties";
}
