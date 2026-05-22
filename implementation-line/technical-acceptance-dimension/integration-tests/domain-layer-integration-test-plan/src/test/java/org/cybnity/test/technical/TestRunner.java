package org.cybnity.test.technical;

import org.cybnity.test.technical.accesscontrol.adapter.impl.keycloak.AccessControlTest;
import org.testng.TestNG;

/**
 * Main test suite class relative to Access Control domain layer that defined the perimeter of technical integration tests.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestNG runner = new TestNG();
        runner.setTestClasses(new Class[]{AccessControlTest.class});
        runner.run();
    }
}
