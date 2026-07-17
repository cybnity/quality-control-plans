package org.cybnity.application.keycloak.admin.impl.test.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cybnity.application.accesscontrol.adapter.api.admin.IAccessAdminAdapter;
import org.cybnity.application.accesscontrol.adapter.api.admin.OperationException;
import org.cybnity.application.accesscontrol.adapter.api.model.TenantDTO;
import org.cybnity.application.keycloak.admin.impl.test.runner.RealmCreationTestCase;
import org.cybnity.application.keycloak.admin.impl.test.util.KeycloakClientHelper;
import org.cybnity.framework.UnoperationalStateException;
import org.cybnity.keycloak.api.KeycloakAPIResponseCode;
import org.cybnity.keycloak.api.KeycloakInterpretableContext;
import org.cybnity.keycloak.api.ResponseCodeIdentificationExpression;
import org.cybnity.keycloak.domain.model.RealmWithDefaultExtendedResourcesBuilder;
import org.cybnity.test.commons.ContextualizedTest;
import org.cybnity.test.commons.DomainLayerIntegrationProvider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Scenario steps regarding test case.
 */
public class CreateRealmSteps extends ContextualizedTest {

    private IAccessAdminAdapter adapter;
    private Keycloak adminClient;

    /**
     * Temporary test data created regarding tenant (= Keycloak realm element)
     * Equals to a temporary test data cache.
     */
    private final List<String> testTenantsCache = new ArrayList<>();

    TenantDTO successTestData_createdTenant;

    private boolean invalidTenantLabelCreationAttemptResult = false;

    /**
     * Default constructor required by Cucumber.
     *
     * @throws Exception When environment variables read problem.
     */
    public CreateRealmSteps() throws Exception {
        super(org.cybnity.application.keycloak.admin.impl.test.runner.RealmCreationTestCase.ENV_PROPERTY_FILEPATH);
    }

    @Before
    public void setup() throws UnoperationalStateException, Exception {
        // Define environment variables required by this test execution (e.g; keycloak admin adapter) from properties file
        this.setupEnvironmentVariables(RealmCreationTestCase.ENV_PROPERTY_FILEPATH); // Context is loaded with environment variables
        invalidTenantLabelCreationAttemptResult = false; // Re-init default value of scenario result
        getEnvironmentVariables().execute(() -> {
            this.adapter = DomainLayerIntegrationProvider.instance().getAccessAdminAdapter(this.getContext());
            this.adminClient = KeycloakClientHelper.getKeycloakClient(this.getContext());
            logger().info("CreateRealmSteps setup executed successfully");
        });
    }

    @After
    public void cleanup() {
        // Data cleaning after this test execution have been finished

        // Delete all test data about tenant names
        for (String testTenant : testTenantsCache) {
            try {
                this.adapter.deleteTenant(testTenant, true /* Force deletion and any test sub-data*/);
            } catch (Exception e) {
                logger.warn("Failed to delete tenant " + testTenant);
            }
        }
        testTenantsCache.clear(); // Delete all cached scenario data
        this.adapter.freeUpResources();
        this.adminClient.close();

        logger().info("CreateRealmSteps cleanup executed successfully");
    }

    @Given("none tenant named {string} is existing into Keycloak SSO system")
    public void noneTenantNamedIsExistingIntoKeycloakSSOSystem(String tenantName) throws OperationException {
        this.adapter.deleteTenant(tenantName, true);
    }

    @When("the user request creation of a tenant named as {string}")
    public void theUserRequestCreationOfATenantNamedAs(String tenantName) throws OperationException {
        // Feed test data cache
        testTenantsCache.add(tenantName);
        // Create a Realm into Keycloak, automatically translated into TenantDTO by API adapter
        successTestData_createdTenant = this.adapter.createTenant(tenantName);
    }

    @Then("keycloak SSO system confirm the realm success creation")
    public void keycloakSSOSystemConfirmTheTenantSuccessCreation() {
        Assert.assertNotNull(successTestData_createdTenant);
    }

    @And("a realm element is registered and managed into Keycloak SSO system")
    public void a_realm_element_is_registered_and_managed_into_keycloak_sso_system() throws OperationException {
        // Check that a default status of the tenant is defined (e.g; TRUE when Tenant enabled, or FALSE when disabled by default)
        Assert.assertNotNull(successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.ACTIVITY_STATUS), "shall be known from Keycloak!");
        String labelToCheckIntoKeycloak = successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.LABEL);
        // Search the realm into Keycloak with same label for check that is have been really created
        TenantDTO result = adapter.findTenantByLabel(labelToCheckIntoKeycloak);
        Assert.assertNotNull(result, "shall have been found!");
        Assert.assertEquals(successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.IDENTIFIED_BY), result.valueOfProperty(TenantDTO.PropertyAttributeKey.IDENTIFIED_BY), "Invalid found realm identity with equals label!");
    }

    @And("the realm technical identification data are received from Keycloak")
    public void theRealmTechnicalIdentificationDataAreReceivedFromKeycloak() {
        // Check that any technical data required as configuration from CYBNITY side have been loaded into the DTO for future storage by Access Control domain
        Assert.assertNotNull(successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.LAST_UPDATED_AT), "shall include traceability and auditable date of committed transaction with Keycloak!");
        Assert.assertNotNull(successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.IDENTIFIED_BY), "shall be known resource url based identifier from Keycloak!");
    }

    @And("the created realm label does not include any blank character")
    public void theCreatedRealmLabelDoesNotIncludeAnyBlankCharacter() {
        String keycloakFormattedLabel = successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.LABEL);
        Assert.assertFalse(keycloakFormattedLabel.contains(" "), "shall not contain blank character to be usable over URL!"); // Potential unusability for future call over Keycloak REST API
    }

    @And("default realm extended resources created like {string}")
    public void defaultRealmExtendedResourcesCreatedLike(String ui_layer_client_name) {
        String realmLabel = successTestData_createdTenant.valueOfProperty(TenantDTO.PropertyAttributeKey.LABEL);

        RealmResource realm =adminClient.realm(realmLabel);
        RealmRepresentation createdOriginRealm = realm.toRepresentation();
        // --- Check created extended configuration relative to default realm ---
        String msg = "shall have been automatically created during tenant creation as realm default configuration element!";

        // Check realm general settings have been defined
        Map<String, String> generalSettings = createdOriginRealm.getAttributes();
        Assert.assertNotNull(generalSettings.get(RealmWithDefaultExtendedResourcesBuilder.FRONTEND_URL), msg);

        // Check browser security headers have been defined
        Map<String, String> browserSecurityHeaders = createdOriginRealm.getBrowserSecurityHeaders();
        Assert.assertNotNull(browserSecurityHeaders.get(RealmWithDefaultExtendedResourcesBuilder.X_FRAME_OPTIONS), msg);
        Assert.assertNotNull(browserSecurityHeaders.get(RealmWithDefaultExtendedResourcesBuilder.CONTENT_SECURITY_POLICY), msg);

        // Check that default clients have been created for Realm
        List<ClientRepresentation> defaultClients = realm.clients().findAll();
        Assert.assertNotNull(defaultClients, msg);
        // Search web-reactive-frontend-system client created by default as integration config for UI layer components
        boolean found = false;
        Iterator<ClientRepresentation> clientIt = defaultClients.iterator();
        while(!found && clientIt.hasNext()) {
            ClientRepresentation client = clientIt.next();
            found = client.getClientId().equalsIgnoreCase(ui_layer_client_name);
        }
        Assert.assertTrue(found, msg);
    }

    @Given("a realm already exist in Keycloak SSO system that is named {string}")
    public void aRealmAlreadyExistInKeycloakSsoSystemThatIsNamed(String existingTenantName) throws OperationException {
        testTenantsCache.add(existingTenantName); // Feed test data cache
        invalidTenantLabelCreationAttemptResult = false; // Re-init
        // Add test tenant data as existing initial state into Keycloak
        this.adapter.createTenant(existingTenantName);
    }

    @When("the user request creation of a tenant with name equals to {string}")
    public void the_user_request_creation_of_a_tenant_with_name_equals_to(String tenantName) {
        // Feed test data cache
        testTenantsCache.add(tenantName);
        // Attempt creation of a new tenant with same name as previous existing in Keycloak
        try {
            this.adapter.createTenant(tenantName);
            invalidTenantLabelCreationAttemptResult = false;
            assert false; // Invalid performed operation that should have been rejected for cause of duplicate realm (with same name already existing)
        } catch (OperationException op) {
            String errorMsg = op.getMessage();
            // Identify if is a valid rejection cause
            ResponseCodeIdentificationExpression exp = new ResponseCodeIdentificationExpression(errorMsg);
            KeycloakAPIResponseCode error = (KeycloakAPIResponseCode) exp.interpret(new KeycloakInterpretableContext());

            if (KeycloakAPIResponseCode.CONFLICT == error /* HTTP 409 conflict exception */ || op.getCause() instanceof IllegalArgumentException /* blank character included*/) {
                invalidTenantLabelCreationAttemptResult = true;
                assert true;// Normal rejected operation for cause of existing same tenant named in Keycloak
            } else {
                invalidTenantLabelCreationAttemptResult = false;
                assert false;  // Invalid cause of rejected creation
            }
        }
    }

    @Then("keycloak SSO system reject the creation demand")
    public void keycloak_sso_system_reject_the_creation_demand() {
        Assert.assertTrue(invalidTenantLabelCreationAttemptResult, "Creation shall have been refused for cause of invalid label!");
    }

}
