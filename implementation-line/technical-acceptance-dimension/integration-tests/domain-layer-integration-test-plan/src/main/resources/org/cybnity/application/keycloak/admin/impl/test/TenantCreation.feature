Feature: Unique tenant creation
  Creation management of tenant (= Keycloak realm) based on unique name into Keycloak SSO system over its API client(s)

  @successTest @integrationTest @functionalTest
  Scenario Outline: Add a new tenant to the SSO system with success
    Given none tenant named <tenant_name> is existing into Keycloak SSO system
    When the user request creation of a tenant named as <tenant_name>
    Then keycloak SSO system confirm the tenant success creation via sent <tenant_creation_confirmed_event_type>
    And a realm element is registered and managed into Keycloak SSO system
    And the tenant technical identification data are received from Keycloak into <tenant_identification_data_type>
    And the created tenant label does not include any blank character
    And keycloak SSO system notify all systems that a tenant named <tenant_name> is existing via sent <new_tenant_created_event>

    Examples:
      | tenant_name     | tenant_creation_confirmed_event_type | tenant_identification_data_type | new_tenant_created_event |
      | "CYBNITY"       | "TENANT_CREATION_CONFIRMED_EVENT"    | "tenantIdentity"                | "TENANT_CREATED_EVENT"   |
      | "C Y B N I T Y" | "TENANT_CREATION_CONFIRMED_EVENT"    | "tenantIdentity"                | "TENANT_CREATED_EVENT"   |

  @exceptionTest @functionalTest
  Scenario Outline: Attempt to create a tenant with the same name as an existing tenant
    Given a tenant already exist in Keycloak SSO system that is named <existing_tenant_name>
    When the user request creation of a tenant with name equals to <tenant_name>
    Then keycloak SSO system reject the creation demand via sent <tenant_creation_rejected_event_type>
    And the cause of rejection is responded by Keycloak via <tenant_creation_reject_cause_type> (as audit log)

    Examples:
      | existing_tenant_name | tenant_name | tenant_creation_rejected_event_type | tenant_creation_reject_cause_type |
      | "CYBNITY2"           | "CYBNITY2"  | "TENANT_CREATION_REJECTED_EVENT"    | "rejectionCause"                  |
      | "cybnity2"           | "CYBNITY2"  | "TENANT_CREATION_REJECTED_EVENT"    | "rejectionCause"                  |
      | "CYBNITY2"           | "cybnity2"  | "TENANT_CREATION_REJECTED_EVENT"    | "rejectionCause"                  |
      | "CYBNITY2"           | "cybnity 2" | "TENANT_CREATION_REJECTED_EVENT"    | "rejectionCause"                  |
