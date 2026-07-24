Feature: Realm administration events handling
  Handling of events occurred when realm configuration is modified into Keycloak SSO system (via UI or API)

  @successTest @integrationTest @technicalTest
  Scenario Outline: Handle success creation event regarding a new realm
    Given none tenant named <tenant_name> is existing into Keycloak SSO system
    When keyclock IDM system record a new realm resource named as <realm_name>
    Then keycloak SSO system confirm the success creation via sent <realm_creation_confirmed_event_type>
    And the realm technical identification data is received from Keycloak
    And a date of event occcurred is provided by the received event

    Examples:
      | realm_name | realm_creation_confirmed_event_type |
      | "CYBNITY"  | "REALM_CREATION_CONFIRMED_EVENT"    |
