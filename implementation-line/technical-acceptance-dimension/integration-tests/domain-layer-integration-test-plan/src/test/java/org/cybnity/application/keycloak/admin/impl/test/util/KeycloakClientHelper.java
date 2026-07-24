package org.cybnity.application.keycloak.admin.impl.test.util;

import org.cybnity.application.accesscontrol.adapter.impl.keycloak.admin.AdminConfigurationVariable;
import org.cybnity.framework.IContext;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

/**
 * Provider of utility tools usable during the tests (e.g; for direct check of changed data into external tools).
 */
public class KeycloakClientHelper {

    /**
     * Get a Keycloak client allowing direct access to SSO system over Admin Client API.
     *
     * @param context Mandatory context allowing to read environment variables hosting the keycloak server configuration to contact.
     * @return A client.
     * @throws IllegalArgumentException When mandatory parameter is missing.
     */
    public static Keycloak getKeycloakClient(IContext context) throws IllegalArgumentException {
        if (context == null) throw new IllegalArgumentException("context required");
        // See admin client library source code project at https://github.com/keycloak/keycloak-client/blob/main/admin-client/src/main/java/org/keycloak/admin/client/Keycloak.java
        // the builder implementation uses a default RestEasy client builder settings
        return KeycloakBuilder.builder()
                // Read current up-to-date administration client configuration allowing Keycloak Admin Client API usage of master realm (supporting any environment configuration HOT changes)
                .serverUrl(context.get(AdminConfigurationVariable.KEYCLOAK_SERVER_URL))
                .realm(context.get(AdminConfigurationVariable.REALM_MASTER_NAME /* Keycloak default master realm as currently configured into Keycloak server */))
                .clientId(context.get(AdminConfigurationVariable.REALM_MASTER_CLIENTID))
                .grantType(context.get(AdminConfigurationVariable.REALM_MASTER_GRANT_TYPE))
                .username(context.get(AdminConfigurationVariable.REALM_MASTER_USERNAME))
                .password(context.get(AdminConfigurationVariable.REALM_MASTER_PASSWORD))
                .build();
    }
}
