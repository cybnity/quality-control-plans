package org.cybnity.application.keycloak.admin.impl.test.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cybnity.accesscontrol.domain.model.TenantDTO;
import org.cybnity.application.accesscontrol.adapter.api.admin.IAccessAdminAdapter;
import org.cybnity.application.accesscontrol.adapter.api.admin.OperationException;
import org.cybnity.application.keycloak.admin.impl.test.runner.TenantCreationCase;
import org.cybnity.framework.UnoperationalStateException;
import org.cybnity.test.commons.ContextualizedTest;
import org.cybnity.test.commons.DomainLayerIntegrationProvider;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Scenario steps regarding test case.
 */
public class CreateTenantSteps extends ContextualizedTest {

    private IAccessAdminAdapter adapter;

    /**
     * Temporary test data created regarding tenant (= Keycloak realm element)
     * Equals to a temporary test data cache.
     */
    private List<String> testTenantsCache = new ArrayList<>();

    /**
     * Default constructor required by Cucumber.
     *
     * @throws Exception When environment variables read problem.
     */
    public CreateTenantSteps() throws Exception {
        super(TenantCreationCase.ENV_PROPERTY_FILEPATH);
    }

    @Before
    public void setup() throws UnoperationalStateException, Exception {
        // Define environment variables required by this test execution (e.g; keycloak admin adapter) from properties file
        this.setupEnvironmentVariables(TenantCreationCase.ENV_PROPERTY_FILEPATH); // Context is loaded with environment variables

        getEnvironmentVariables().execute(() -> {
            this.adapter = DomainLayerIntegrationProvider.instance().getAccessAdminAdapter(this.getContext());
            logger().info("CreateTenantSteps setup executed successfully");
        });
    }

    @After
    public void cleanup() {
        // Data cleaning after this test execution have been finished

        // Delete all test data about tenant names
        for (String testTenant : testTenantsCache) {
            this.adapter.deleteTenant(testTenant, true /* Force deletion and any test sub-data*/);
        }
        testTenantsCache.clear(); // Delete all cached scenario data
        logger().info("CreateTenantSteps cleanup executed successfully");
    }

    @Given("none tenant named {string} is existing into Keycloak SSO system")
    public void noneTenantNamedIsExistingIntoKeycloakSSOSystem(String tenantName) {
        this.adapter.deleteTenant(tenantName, true);
    }

    @When("the user request creation of a tenant named as {string}")
    public void theUserRequestCreationOfATenantNamedAs(String tenantName) throws OperationException {
        // Create a Realm into Keycloak, automatically translated into TenantDTO by API adapter
        TenantDTO successTestData_createdTenant = this.adapter.createTenant(tenantName);
        Assert.assertNotNull(successTestData_createdTenant);
        String keycloakFormattedLabel = successTestData_createdTenant.getLabel();
        // Feed test data cache
        testTenantsCache.add(keycloakFormattedLabel);

        // Check that status of tenant is technically known and respect mandatory expected integrity as Keycloak Admin API output
        Assert.assertFalse(keycloakFormattedLabel.contains(" "), "shall not contain blank character to be usable over URL!"); // Potential unusability for future call over Keycloak REST API
        Assert.assertNotNull(successTestData_createdTenant.occurredAt(), "shall include traceability and auditable date of committed transaction with Keycloak!");
        Assert.assertNotNull(successTestData_createdTenant.getCurrentStatus(), "shall be known from Keycloak!");
    }

    @Then("keycloak SSO system confirm the tenant success creation via sent {string}")
    public void keycloakSSOSystemConfirmTheTenantSuccessCreationViaSent(String eventType) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("a realm element is registered and managed into Keycloak SSO system")
    public void a_realm_element_is_registered_and_managed_into_keycloak_sso_system() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("the tenant technical identification data are received from Keycloak into {string}")
    public void theTenantTechnicalIdentificationDataAreReceivedFromKeycloakInto(String tenantIdentificationDataType) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("the created tenant label does not include any blank character")
    public void theCreatedTenantLabelDoesNotIncludeAnyBlankCharacter() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("keycloak SSO system notify all systems that a tenant named {string} is existing via sent {string}")
    public void keycloakSSOSystemNotifyAllSystemsThatATenantNamedIsExistingViaSent(String tenantName, String newTenantCreatedEvent) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a tenant already exist in Keycloak SSO system that is named {string}")
    public void aTenantAlreadyExistInKeycloakSsoSystemThatIsNamed(String existingTenantName) throws OperationException {
        // Add test tenant data as existing initial state into Keycloak
        this.adapter.createTenant(existingTenantName);
        testTenantsCache.add(existingTenantName); // Feed test data cache
    }

    @When("the user request creation of a tenant with name equals to {string}")
    public void the_user_request_creation_of_a_tenant_with_name_equals_to(String tenantName) {
        // Attempt creation of a new tenant with same name as previous existing in Keycloak
        try {
            this.adapter.createTenant(tenantName);
            assert false; // Invalid performed operation that should have been rejected for cause of duplicate realm (with same name already existing)
        } catch (OperationException op) {
            assert true; // Normal rejected operation for cause of existing same tenant named in Keycloak
        }
    }

    @Then("keycloak SSO system reject the creation demand via sent {string}")
    public void keycloak_sso_system_reject_the_creation_demand_via_sent(String tenantCreationRejectedEventType) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the cause of rejection is responded by Keycloak via {string} \\(as audit log)")
    public void the_cause_of_rejection_is_responded_by_keycloak_via_as_audit_log(String tenantCreationRejectCauseType) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
