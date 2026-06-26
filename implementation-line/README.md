## PURPOSE
Presentation of the Test Plans projects dedicated to build and deliver CYBNITY applications test specification and test implementation components versions.

Test feature specifications are delivered according to requirements and specification identified by CYBNITY application requirements.

> [!NOTE]  
> The unit testing practice is not supported by this project. Each CYBNITY Software project is ensuring the development and execution of its dedicated Unit Tests during the build and delivery process. So, the scope of unit tests plans is not maintained over this project.

### Sources Structure
Test plan projects are structured and built according to standards:
- [TestNG](https://testng.org/): testing framework reused by CYBNITY for management of test plans (e.g. facilities for grouping and prioritizing tests; enhanced reporting of Cucumber test executions reports)
- [Cucumber](https://cucumber.io/): tool for design and running of automated acceptance tests, written in plan language (BDD support)
- Maven: tool for Java test plan and components build accoding to a [standard Maven project structure](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html) to produce and deliver CYBNITY test applications
- Docker: system containers and images templates are managed by executable test project(s) (e.g via auto-generated Dockerfile by Maven process)

The test projects are supported by an Official TechStack version defining the authorized technologies used by any the test software sub-project.

### Utility sub-projects
Complementary Maven sub-projects are managed as common libraries reusable by the test plan projects.

Those artifacts are dedicated to host common utility components (e.g; helpers like factories, builders, common configuration elements):
- [commons](commons) including utility implementation components that can be reused commonly by any test plan component

# QUALITY CONTROL DIMENSIONS & TEST COMPONENTS
The sub-projects managed in this area are about the test components ensuring quality control.

The source codes managed in the independent sub-projects (test components) are dedicated to host a test artifact (e.g executable library) per quality plan project according to its test dimension.

The managed quality control dimensions are organized as:
- [Functional acceptance dimension](functional-acceptance-dimension)
- [Technical acceptance dimension](technical-acceptance-dimension)

## Functional Control Quality
Sub-projects allowing build of Java libraries ensuring definition and execution of test plan to validate criteria of evaluated CYBNITY Application parts relative to functional requirements.

Each test component manages its source codes structure:
- [UI Modules capabilities test plan component](functional-acceptance-dimension/ui-modules-capabilities-test-plan)
- [Application Modules processes test plan component](functional-acceptance-dimension/application-modules-processes-test-plan)
- [Feature Components test plan component](functional-acceptance-dimension/feature-components-test-plan)

## Technical Control Quality
Sub-projects allowing build of Java libraries ensuring definition and execution of test plan to validate criteria of evaluated CYBNITY Application parts relative to non-functional requirements.

The test sub-projects are grouped according to their type of technical concerns and acceptance type asserved.

Each test component manages its source codes structure.

- [INTEGRATION ACCEPTANCE](technical-acceptance-dimension/integration-tests)
  - [UI Layer integration test plan](technical-acceptance-dimension/integration-tests/ui-layer-integration-test-plan)
  - [Application Layer integration test plan](technical-acceptance-dimension/integration-tests/application-layer-integration-test-plan)
  - [Domain Layer integration test plan](technical-acceptance-dimension/integration-tests/domain-layer-integration-test-plan)

- [SECURITY ACCEPTANCE](technical-acceptance-dimension/security-tests)
  - [Cybersecurity test plan](technical-acceptance-dimension/security-tests/cybersecurity-test-plan)

- [PERFORMANCE ACCEPTANCE](technical-acceptance-dimension/performance-tests)
  - [Performance test plan](technical-acceptance-dimension/performance-tests/performance-test-plan)

- [USABILITY ACCEPTANCE](technical-acceptance-dimension/usability-tests)
  - [UI usability test plan](technical-acceptance-dimension/usability-tests/ui-usability-test-plan)
  
# RUNNABLE TEST CAMPAIGNS
Several test modules are developed as libraries which can be deployed and executed in standalone approach (e.g integration test plan modules deployed and executed on Continuous Integration platform; smoke test application deployed and executed on Production environment).

A Maven profile is defined per type of environment where CYBNITY platform version can be evaluated and tested by one or several set of test plans.

For allow flexibility in terms of reusage of test plans according to specific perimeters of quality control required by a CYBNITY Platform deployment phase (e.g pre-selected test plan to be executed on test environment dedicated to integration validation) and-or according to a goal of conformity (e.g pre-selected set of test plans to be executed as smoke tests after a new CYBNITY Platform version installation), Maven profiles are maintained per goal or per execution environment, allowing to define assembled set of test plans.

Each Maven profile shall define its scope of tests to be executed from defined rules of test scenarios selection (via __includes__ or __excludes__ rules).

> [!TIP]
> The execution of a test campaign can be assigned and defined into a Continuous Integration chain (e.g GitHub actions of CYBNITY systems projects) and-or by Continuous Delivery chain step (e.g Fleet CD project).

## Maven Profiles per Environment (execution environment)
One profile per supported environment is defined into the [pom.xml](pom.xml) file of the implementation-line:

- __dev-deploy-environment__ profile
- __qa-environment__ profile
- __uat-environment__ profile
- __perf-environment__ profile

## Maven Profiles per Quality Control Goal (quality stage)
One profile per supported quality verification phase is defined into the [pom.xml](pom.xml) file of the implementation-line:

- __technical-quality-stage__ profile (phase of technical quality validation of a CYBNITY software version)
- __integration-quality-stage__ profile (phase of integration quality control of a CYBNITY software components versions) 
- __functional-quality-stage__ profile (step of CYBNITY software solution behavior validation by testers community)
- __performance-quality-stage__ profile (step of CYBNITY software solution performance evaluation and non-regression control)

## How to active a quality control Plan
All supported profiles are documented into the [pom.xml](pom.xml) file of the implementation-line.

According to the environment available and-or the type of quality control expected to be executed by a CYBNITY software maintainer (e.g; component Developer during coding activities; system version Deployer during system delivery configuration activities), the Maven profiles activation are enabled according to:

- For activate an execution environment profile: define an environment variable onto the workstation where the test plan execution is launched.
  - For example: to activate the __dev-deploy-environment__ profile (execute test plans into a context with existing shared K8S cluster or external systems accessible from the current workstation), set the environment variable __TEST_ENV__ with the value __dev-deploy__ onto the workstation <ins>BEFORE TO LAUNCH the test plans execution</ins>:
  ```console
    export TEST_ENV=dev-deploy
  ```

- For activate a quality stage profile: define an environment variable onto the workstation where the test plan execution is launched.
  - For example: to activate the __integration-quality-stage__ profile (execute the test plans dedicated to control technical integration of CYBNITY software components and-or systems between them), set the environment variable __STAGE__ with the value __int-qa__ onto the workstation <ins>BEFORE TO LAUNCH the test plans execution</ins>:
  ```console
    export STAGE=int-qa
  ```

See Maven documentation about [explicit profile activation](https://maven.apache.org/guides/introduction/introduction-to-profiles.html#explicit-profile-activation) to identify other solutions (e.g; for permanent activated profiles considered by an IDE used by developer) allowing to activate profiles from the __settings.xml__ file on the workstation executing the test plans.

## How to check the current active Maven profiles
Execution the command line to show the current enabled profiles (e.g; environment and quality stage enabled profiles):
```console
  mvn help:active-profiles
```

# DEPLOYABLE TEST SYSTEMS
Several types of test systems are developed and containerized as executable test systems which can be deployed and operated according to the deployment model required by an execution context.

For example, by default, a library component relative to a test plan can be directly executed by a CI/CD process without need of specific packaged test system.

In another case of a standalone test system is required for test execution from a standalone machine external to a CYBNITY Platform cluster, a containerized test system is packaged as a Java standalone application which can be installed into a Docker image.

The [test-systems](test-systems) folder is here to host any type of assembled and executable type of test module (e.g Docker image, Jar application) supporting the development and deployment teams in charge of acceptance of CYBNITY application versions. 

#
[Back To Home](../README.md)
