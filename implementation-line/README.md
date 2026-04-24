## PURPOSE
Presentation of the Test Plans projects dedicated to build and deliver CYBNITY applications test specification and test implementation components versions.

Test feature specifications are delivered according to requirements and specification identified by CYBNITY application requirements.

> [!NOTE]  
> The unit testing practice is not supported by this project. Each CYBNITY Software project is ensuring the development and execution of its dedicated Unit Tests during the build and delivery process. So, the scope of unit tests plans is not maintained over this project.

### Sources Structure
Test plan projects are structured and built according to standards:
- Maven: Java test components using a [standard Maven project structure](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
- Docker: system containers and images templates are managed by executable test project (e.g via auto-generated Dockerfile by Maven process)

The test projects are supported by an Official TechStack version defining the authorized technologies used by any the test software sub-project.

# QUALITY CONTROL DIMENSIONS & TEST COMPONENTS
The sub-projects managed in this area are about the test components ensuring quality control.

The source codes managed in the independent sub-projects (test components) are dedicated to host a test artifact (e.g executable library) per quality plan project according to its test dimension.

The managed quality control dimensions are organized as:
- [Functional acceptance dimension](functional-acceptance-dimension)
- [Technical acceptance dimension](teschnical-acceptance-dimension)

## Functional Control Quality
Sub-projects allowing build of Java libraries ensuring definition and execution of test plan to validate criteria of evaluated CYBNITY Application parts relative to functional requirements.

Each test component manages its source codes structure:
- [UI Modules capabilities test plan component](ui-modules-capabilities-test-plan)
- [Application Modules processes test plan component](application-modules-processes-test-plan)
- [Feature Components test plan component](feature-components-test-plan)

## Technical Control Quality
Sub-projects allowing build of Java libraries ensuring definition and execution of test plan to validate criteria of evaluated CYBNITY Application parts relative to non-functional requirements.

The test sub-projects are grouped according to their type of technical concerns and acceptance type asserved.

Each test component manages its source codes structure.

- [INTEGRATION ACCEPTANCE](integration-tests)
  - [UI Layer integration test plan](integration-tests/ui-layer-integration-test-plan)
  - [Application Layer integration test plan](integration-tests/application-layer-integration-test-plan)
  - [Domain Layer integration test plan](integration-tests/domain-layer-integration-test-plan)

- [SECURITY ACCEPTANCE](security-tests)
  - [Cybersecurity test plan](security-tests/cybersecurity-test-plan)

- [PERFORMANCE ACCEPTANCE](performance-tests)
  - [Performance test plan](performance-tests/performance-test-plan)

- [USABILITY ACCEPTANCE](usability-tests)
  - [UI usability test plan](usability-tests/ui-usability-test-plan)
  
# RUNNABLE TEST CAMPAIGNS
Several test modules are developed as libraries which can be deployed and executed in standalone approach (e.g integration test plan modules deployed and executed on Continuous Integration platform; smoke test application deployed and executed on Production environment).

A Maven profile is defined per type of environment where CYBNITY platform version can be evaluated and tested by one or several set of test plans.

For allow flexibility in terms of reusage of test plans according to specific perimeters of quality control required by a CYBNITY Platform deployment phase (e.g pre-selected test plan to be executed on test environment dedicated to integration validation) and-or according to a goal of conformity (e.g pre-selected set of test plans to be executed as smoke tests after a new CYBNITY Platform version installation), Maven profiles are maintained per goal or per execution environment, allowing to define assembled set of test plans.

Each Maven profile shall define its scope of tests to be executed from defined rules of test scenarios selection (via __includes__ or __excludes__ rules).

> [!TIP]
> The execution of a test campaign can be assigned and defined into a Continuous Integration chain (e.g GitHub actions of CYBNITY systems projects) and-or by Continuous Delivery chain step (e.g Fleet CD project).

## Maven Profiles per Environment
One profile per supported environment is defined into the [pom.xml](pom.xml) file of the implementation-line:

- __dev-deploy-environment__ profile
- __qa-environment__ profile
- __uat-environment__ profile
- __perf-environment__ profile

## Maven Profiles per Quality Control Goal

- __technical-quality-stage__ profile (phase of technical quality validation of a CYBNITY software version)
- __integration-quality-stage__ profile (phase of integration quality control of a CYBNITY software components versions) 
- __user-acceptance-test-stage__ profile (step of CYBNITY software solution behavior validation by testers community)
- __performance-acceptance-test-stage__ profile (step of CYBNITY software solution performance evaluation and non-regression control)

# DEPLOYABLE TEST SYSTEMS
Several types of test systems are developed and containerized as executable test systems which can be deployed and operated according to the deployment model required by an execution context.

For example, by default, a library component relative to a test plan can be directly executed by a CI/CD process without need of specific packaged test system.

In another case of a standalone test system is required for test execution from a standalone machine external to a CYBNITY Platform cluster, a containerized test system is packaged as a Java standalone application which can be installed into a Docker image.

The [test-systems](test-systems) folder is here to host any type of assembled and executable type of test module (e.g Docker image, Jar application) supporting the development and deployment teams in charge of acceptance of CYBNITY application versions. 

#
[Back To Home](../README.md)
