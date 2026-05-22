package org.cybnity.test.technical.accesscontrol.adapter.impl.keycloak;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.cybnity.application.accesscontrol.adapter.impl.keycloak.SSOAdapterKeycloakImpl;
import org.cybnity.framework.Context;
import org.cybnity.framework.IContext;
import org.cybnity.framework.UnoperationalStateException;
import org.testng.annotations.*;

import java.util.logging.Logger;


//@CucumberOptions(plugin = "message:target/cucumber-report.ndjson")
//@CucumberOptions(features="src/test/resources/features",glue="stepDefinitions",tags="@Test01",plugin= {"pretty", "html:target/cucumber-reports" },monochrome=true)
public class AccessControlTest extends AbstractTestNGCucumberTests {

    private IContext context;
    private Logger logger;
    private SSOAdapterKeycloakImpl adapter;

    @BeforeTest
    public void beforeTest() throws UnoperationalStateException {
        // Data prepared when this test execution is started
        this.context = new Context();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.adapter = new SSOAdapterKeycloakImpl(this.context);
        System.out.println("AccessControlTestSuite beforeTest");
    }

    @AfterTest
    public void afterTest() {
        // Data cleaning after this test have been executed
        System.out.println("AccessControlTestSuite afterTest");
    }

    /**
     * Verify that adapter configuration is defined are ready for connection to the Keycloak instance
     */
    @Test
    public void checkHealthyState() throws UnoperationalStateException {
        System.out.println("AccessControlTestSuite checkHealthyState test executed");
        adapter.checkHealthyState();
    }
}
