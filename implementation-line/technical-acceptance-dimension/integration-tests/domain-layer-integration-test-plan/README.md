# TEST PLAN EXECUTION

## From command line
Execution of Java application test plan over TestNG can be started via:
```shell
java -jar target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar -testjar target/domain-layer-integration-test-plan-1.0.0-tests.jar
```

Specific test suite execution can be called via additional `-xmlpathinjar` argument:
```shell
java -jar target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar -testjar target/domain-layer-integration-test-plan-1.0.0-tests.jar -xmlpathinjar suites/ac_integration_suite.xml
```