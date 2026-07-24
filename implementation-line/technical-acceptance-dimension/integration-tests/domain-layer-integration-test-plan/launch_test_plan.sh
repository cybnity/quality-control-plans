#!/bin/bash

sh set_environment_variables.sh

echo "Start test plan execution..."

#java -cp target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar org.cybnity.application.keycloak.admin.impl.test.TestPlan

java -jar target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar -testjar target/domain-layer-integration-test-plan-1.0.0-tests.jar -xmlpathinjar testng.xml