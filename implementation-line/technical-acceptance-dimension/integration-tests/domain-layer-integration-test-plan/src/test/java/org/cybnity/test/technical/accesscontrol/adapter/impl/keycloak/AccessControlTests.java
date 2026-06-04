package org.cybnity.test.technical.accesscontrol.adapter.impl.keycloak;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.cybnity.application.accesscontrol.adapter.impl.keycloak.SSOAdapterKeycloakImpl;
import org.cybnity.framework.Context;
import org.cybnity.framework.IContext;
import org.cybnity.framework.UnoperationalStateException;
import org.testng.annotations.*;

import java.util.logging.Logger;

/**
 * This class executes all scenarios of the same package as the runner.
 * By default, glue code is also assumed to be in the same package.
 */
// See "Using plugins" chapter of https://cucumber.io/docs/cucumber/api/#using-plugins about available options configuration
@CucumberOptions(
        plugin = {"message:target/cucumber-report.ndjson",
                "html:target/cucumber-reports", "pretty" /* 2 formatter plugins to use */},
        monochrome=true /* Console output from Cucumber in readable format*/
)
public class AccessControlTests extends AbstractTestNGCucumberTests {

    private IContext context;
    private Logger logger;
    private SSOAdapterKeycloakImpl adapter;

    @BeforeTest
    public void beforeTest() throws UnoperationalStateException {
        // Data prepared when this test execution is started
        this.context = new Context();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.adapter = new SSOAdapterKeycloakImpl(this.context);
        System.out.println("AccessControlTests beforeTest");
    }

    @AfterTest
    public void afterTest() {
        // Data cleaning after this test have been executed
        System.out.println("AccessControlTests afterTest");
    }

    /**
     * Verify that adapter configuration is defined are ready for connection to the Keycloak instance
     */
    @Test
    public void checkHealthyState() throws UnoperationalStateException {
        System.out.println("AccessControlTests checkHealthyState test executed");
        adapter.checkHealthyState();
    }
}
