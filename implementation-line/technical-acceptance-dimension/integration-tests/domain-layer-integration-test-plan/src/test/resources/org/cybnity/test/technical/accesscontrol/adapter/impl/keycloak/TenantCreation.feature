Feature: New tenant creation
  Creation management of a tenant based on unique name into Keycloak SSO system over standard API

  @successTest
  Scenario Outline: Add a new tenant to the SSO system with success
    Given none tenant named <tenant_name> is existing into Keycloak SSO system
    When the user request creation of a tenant named as <tenant_name>
    Then keycloak SSO system confirm the tenant success creation via sent <tenant_creation_confirmed_event_type>
    And the tenant technical identification data are received from Keycloak into <tenant_identification_data_type>
    And the created tenant label does not include any blank character
    And keycloak SSO system notify all systems that a tenant named <tenant_name> is existing via sent <new_tenant_created_event>

    Examples:
      | tenant_name     | tenant_creation_confirmed_event_type | tenant_identification_data_type | new_tenant_created_event |
      | "CYBNITY"       | "TENANT_CREATION_CONFIRMED_EVENT"    | "tenantIdentity"                | "TENANT_CREATED_EVENT"   |
      | "C Y B N I T Y" | "TENANT_CREATION_CONFIRMED_EVENT"    | "tenantIdentity"                | "TENANT_CREATED_EVENT"   |
