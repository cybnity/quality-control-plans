package org.cybnity.test.technical.accesscontrol.adapter.impl.keycloak.definitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateTenantSteps {
    @Given("none tenant named {string} is existing into Keycloak SSO system")
    public void noneTenantNamedIsExistingIntoKeycloakSSOSystem(String arg0) {
    }

    @When("the user request creation of a tenant named as {string}")
    public void theUserRequestCreationOfATenantNamedAs(String arg0) {
    }

    @Then("keycloak SSO system confirm the tenant success creation via sent {string}")
    public void keycloakSSOSystemConfirmTheTenantSuccessCreationViaSent(String arg0) {
    }

    @And("the tenant technical identification data are received from Keycloak into {string}")
    public void theTenantTechnicalIdentificationDataAreReceivedFromKeycloakInto(String arg0) {
    }

    @And("the created tenant label does not include any blank character")
    public void theCreatedTenantLabelDoesNotIncludeAnyBlankCharacter() {
    }

    @And("keycloak SSO system notify all systems that a tenant named {string} is existing via sent {string}")
    public void keycloakSSOSystemNotifyAllSystemsThatATenantNamedIsExistingViaSent(String arg0, String arg1) {
    }

}
