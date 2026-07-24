package org.cybnity.application.keycloak.admin.impl.test;

import org.cybnity.application.keycloak.admin.impl.test.runner.KeycloakRealmCreationTestCase;
import org.testng.TestNG;

/**
 * Main test suite class relative to the perimeter of technical integration tests from domain layer.
 * Helper for run of TestNG default test suite (based on embedded testng.xml file defining the test suite executed) from IDE.
 */
public class TestPlan {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(runnableTestSuites());
        testNG.run();
    }

    /**
     * Define the test suites to be performed as test plan.
     *
     * @return A set of tests.
     */
    private static Class<?>[] runnableTestSuites() {
        return new Class[]{
                /* Keycloak adapter tests */
                KeycloakRealmCreationTestCase.class
                // Add here other tests
        };
    }

}
