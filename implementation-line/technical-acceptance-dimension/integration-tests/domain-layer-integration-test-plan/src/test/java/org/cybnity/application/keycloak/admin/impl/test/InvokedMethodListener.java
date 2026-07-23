package org.cybnity.application.keycloak.admin.impl.test;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class InvokedMethodListener implements IInvokedMethodListener {

    private final List<IInvokedMethod> invokedTestMethods = new ArrayList<>();

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        invokedTestMethods.add(method);
    }

    List<IInvokedMethod> getInvokedTestMethods() {
        return invokedTestMethods;
    }
}