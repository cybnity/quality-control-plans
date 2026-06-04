package org.cybnity.test.technical.accesscontrol.adapter.impl.keycloak.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Test tenant creation into a Keycloak instance over the Keycloak Admin API
 * By default, glue code is also assumed to be in the same package.
 */
// See "Using plugins" chapter of https://cucumber.io/docs/cucumber/api/#using-plugins about available options configuration
@CucumberOptions(
        features="src/test/resources/org/cybnity/test/technical/accesscontrol/adapter/impl/keycloak/TenantCreation.feature",
        glue= {"org.cybnity.test.technical.accesscontrol.adapter.impl.keycloak.definitions"} /* package path to step definition file */,
        tags= "@successTest or @exceptionTest" /* Tag value referencing test in feature file from this scenario */,
        plugin = {"message:target/cucumber-report.ndjson",
                "html:target/cucumber-tenant-creation-report.html", "pretty" /* 2 formatter plugins to use */},
        monochrome = true /* Console output from Cucumber in readable format*/)
public class TenantCreationCase extends AbstractTestNGCucumberTests {
}
