package org.cybnity.application.keycloak.admin.impl.test;

import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.cybnity.application.keycloak.admin.impl.test.runner.RealmCreationTestCase;
import org.testng.TestNG;
import org.testng.annotations.Test;

/**
 * Main test suite class relative to the perimeter of technical integration tests from domain layer.
 * Helper for run of TestNG default test suite (based on embedded testng.xml file defining the test suite executed) from IDE.
 */
public class TestRunner {

    private final static InvokedMethodListener listener = new InvokedMethodListener();

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        //testNG.addListener(listener);
        testNG.setXmlPathInJar("classpath:test.xml");
        testNG.setTestClasses(new Class[]{TestRunner.class});
        //testNG.setTestClasses(new Class[]{RealmCreationTestCase.class});
        testNG.run();
    }

    private TestNGCucumberRunner testNGCucumberRunner;

    @Test
    public void runCucumberTest() {
        testNGCucumberRunner = new TestNGCucumberRunner(RealmCreationTestCase.class);
        for (Object[] scenario : testNGCucumberRunner.provideScenarios()) {
            PickleWrapper wrapper = (PickleWrapper) scenario[0];
            testNGCucumberRunner.runScenario(wrapper.getPickle());
        }
    }
}
