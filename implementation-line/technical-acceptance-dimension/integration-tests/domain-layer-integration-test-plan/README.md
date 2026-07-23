# TEST PLAN EXECUTION

## From command line

### Environment Variables Mandatory Definition

Environment variables need to be defined in the runtime system allowing dynamic configuration of CYBNITY components
integrated during the test plan execution.

The required variables are defined into a default `env.properties` file allowing its customization per environment where
test plan can be launched.

#### Test plan execution from command line

To set tests context required variables on current system, execute file `set_environment_variables.sh` script or execute
manually:

```shell
set -a
. ./test-classes/env.properties
set +a
```

To show added variable to current environment, use command line:

```shell
printenv
```

#### Test plan execution on IDE

Before to launch the `org.cybnity.application.keycloak.admin.impl.test.TestRunner` class of an individual TestNG
runner (e.g; `org.cybnity.application.keycloak.admin.impl.test.runner.RealmCreationTestCase`) from an IDE, add all
variables defined by the `env.properties` file into the unit test configuration.

### Test Plan Execution

Execution of Java application test plan over TestNG can be started via:

```shell
java -jar target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar -testjar target/domain-layer-integration-test-plan-1.0.0-tests.jar
```

Specific test suite execution can be called via additional `-xmlpathinjar` argument:

```shell
java -jar target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar -testjar target/domain-layer-integration-test-plan-1.0.0-tests.jar -xmlpathinjar target/test-classes/testng.xml

or

java -jar target/domain-layer-integration-test-plan-1.0.0-test-jar-with-dependencies.jar -testjar target/domain-layer-integration-test-plan-1.0.0-tests.jar -xmlpathinjar testng.xml
```