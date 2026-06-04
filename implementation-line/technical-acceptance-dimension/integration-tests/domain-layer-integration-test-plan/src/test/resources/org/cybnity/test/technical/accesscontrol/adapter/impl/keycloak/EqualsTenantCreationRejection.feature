Feature: Same tenant creation rejection
  Creation attempt rejection of a tenant based on same name

  @exceptionTest
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
