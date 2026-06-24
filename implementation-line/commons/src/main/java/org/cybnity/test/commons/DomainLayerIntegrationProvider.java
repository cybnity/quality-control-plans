package org.cybnity.test.commons;

import org.cybnity.application.accesscontrol.adapter.api.admin.IAccessAdminAdapter;
import org.cybnity.application.accesscontrol.adapter.api.admin.ISSOAdminAdapter;
import org.cybnity.application.accesscontrol.adapter.impl.keycloak.admin.SSOAdminAdapterKeycloakImpl;
import org.cybnity.framework.IContext;
import org.cybnity.framework.UnoperationalStateException;
import org.cybnity.application.accesscontrol.adapter.impl.keycloak.admin.AccessAdminAdapterKeycloakImpl;

/**
 * Utility class providing common components.
 * Unify configuration of reusable components for test plans.
 */
public class DomainLayerIntegrationProvider {

    /**
     * Factory of helper instance.
     *
     * @return An instance.
     */
    public static DomainLayerIntegrationProvider instance() {
        return new DomainLayerIntegrationProvider();
    }

    /**
     * Get an instance of SSO Admin Adapter including configuration based on settings read from a context.
     *
     * @param context Mandatory provider of configuration data relative to defined environment variables.
     * @return An adapter instance ready for use.
     * @throws IllegalArgumentException    When context is null. When mandatory environment variables required for instantiation of the adapter are not found from the context.
     * @throws UnoperationalStateException When any required environment variable is not defined or have not value ready for use.
     */
    public ISSOAdminAdapter getSSOAdminAdapter(IContext context) throws IllegalArgumentException, UnoperationalStateException {
        return new SSOAdminAdapterKeycloakImpl(context);
    }

    /**
     * Get an instance of Access Admin Adapter including configuration based on settings read from a context.
     *
     * @param context Mandatory provider of configuration data relative to defined environment variables.
     * @return An adapter instance ready for use.
     * @throws IllegalArgumentException    When context is null. When mandatory environment variables required for instantiation of the adapter are not found from the context.
     * @throws UnoperationalStateException When any required environment variable is not defined or have not value ready for use.
     */
    public IAccessAdminAdapter getAccessAdminAdapter(IContext context) throws IllegalArgumentException, UnoperationalStateException {
        return new AccessAdminAdapterKeycloakImpl(context);
    }
}
