Feature: Unique realm creation
  Creation management of realm (tenant in CYBNITY context) based on unique name into Keycloak SSO system over its API client(s)

  @successTest @integrationTest
  Scenario Outline: Add a new tenant to the SSO system with success
    Given none tenant named <tenant_name> is existing into Keycloak SSO system
    When the user request creation of a tenant named as <tenant_name>
    Then keycloak SSO system confirm the realm success creation
    And a realm element is registered and managed into Keycloak SSO system
    And the realm technical identification data are received from Keycloak
    And the created realm label does not include any blank character

    Examples:
      | tenant_name |
      | "CYBNITY"   |
      | "Cybnity5"  |
      | "cybnity6"  |

  @exceptionTest @integrationTest
  Scenario Outline: Attempt to create a tenant with the same name as an existing realm
    Given a realm already exist in Keycloak SSO system that is named <existing_tenant_name>
    When the user request creation of a tenant with name equals to <tenant_name>
    Then keycloak SSO system reject the creation demand

    Examples:
      | existing_tenant_name | tenant_name |
      | "CYBNITY2"           | "CYBNITY2"  |
      | "cybnity3"           | "cybnity3"  |
      | "cybnity4"           | "cybnity 4" |
