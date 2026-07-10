package org.cybnity.application.keycloak.admin.impl.test;

import org.cybnity.application.keycloak.admin.impl.test.runner.RealmCreationTestCase;
import org.testng.TestNG;

/**
 * Main test suite class relative to the perimeter of technical integration tests from domain layer.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestNG runner = new TestNG();
        runner.setTestClasses(new Class[]{RealmCreationTestCase.class});
        runner.run();
    }
}
