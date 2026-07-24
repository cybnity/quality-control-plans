package org.cybnity.application.keycloak.admin.impl.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Test about tenant creation feature into a Keycloak instance over the Keycloak Admin API.
 * By default, glue code is also assumed to be in the same package.
 * This is the TestNG runner class that override the scenario method.
 * The TestPlan class serves as the bridge between Cucumber and TestNG.
 */
// See "Using plugins" chapter of https://cucumber.io/docs/cucumber/api/#using-plugins about available options configuration
// features = "src/test/resources/org/cybnity/application/keycloak/admin/impl/test/features/RealmCreation.feature"
@CucumberOptions(
        features = "classpath:org/cybnity/application/keycloak/admin/impl/test/features/RealmCreation.feature",
        glue = {"org.cybnity.application.keycloak.admin.impl.test.definitions"} /* package path to step definition file */,
        tags = "@integrationTest or @functionalTest" /* Tag value referencing test in feature file from this scenario (see https://github.com/cucumber/tag-expressions) */,
        plugin = {"message:cucumber-report.ndjson",
                "html:cucumber-realm-creation-report.html", "pretty" /* 2 formatter plugins to use */},
        monochrome = true /* Console output from Cucumber in readable format*/)
public class KeycloakRealmCreationTestCase extends AbstractTestNGCucumberTests {
}
