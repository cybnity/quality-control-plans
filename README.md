## PURPOSE
Repository dedicated to the quality control projects (e.g; testing applications implementing test scenario plans), with mission to evaluate and report the quality of CYBNITY software components and systems versions.

You can find informations relative to test plans maintenance like:
- Design diagrams regarding organization of test plans and eventual dependencies
- Support to test plans execution according to execution environments targeted
- Test software developed and maintained as test plans reusable Non-Regression quality control systems

# QUALITY CONTROL PROJECTS
## Business Acceptance Criteria
A Business Acceptance Criteria is defining the business needs and linked expectations for each business process, IT operational services or CYBNITY solutions, allowing to maintain their quality control measured by acceptance criteria.

This type of artifact capture the quality acceptance eligible to inclusion into tested solution SLAs.

One or several business acceptance criteria can be linked to a Test Scenario to validate the quality of a feature and/or a system.

```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {
        'background': '#ffffff',
        'fontFamily': 'arial',
        'fontSize': '13px',
        'primaryColor': '#fff',
        'primaryTextColor': '#0e2a43',
        'primaryBorderColor': '#0e2a43',
        'secondaryColor': '#fff',
        'secondaryTextColor': '#0e2a43',
        'secondaryBorderColor': '#fff',
        'tertiaryColor': '#fff',
        'tertiaryTextColor': '#0e2a43',
        'tertiaryBorderColor': '#fff',
        'edgeLabelBackground':'#fff',
        'lineColor': '#0e2a43',
        'titleColor': '#fff',
        'textColor': '#0e2a43',
        'lineColor': '#0e2a43',
        'nodeTextColor': '#0e2a43',
        'nodeBorder': '#0e2a43',
        'noteTextColor': '#fff',
        'noteBorderColor': '#fff'
    },
    'flowchart': { 'curve': 'monotoneX', 'htmlLabels': 'true', 'wrappingWidth': '400' }
  }
}%%
flowchart LR
  b_a_c("#60;#60;Quality Control Dimension#62;#62; \n Business Acceptance Criteria")
  t_case("Test Case") --- t_sc("Test Scenario")
  t_sc --- b_a_c
  functionalPlan("Functional Acceptance Criteria") & technicalPlan("Technical Acceptance Criteria") --->|extends| b_a_c

  classDef dimension fill:#fff, stroke:##0e2a43, color:##0e2a43;
  classDef testelement fill:#0e2a43, color:#fff;
  classDef component fill:#fff, stroke:#e5302a, color:#e5302a, stroke-dasharray: 5 5;
  
  class b_a_c dimension
  class t_case,t_sc testelement
```
## Test Environments
Each test plan can be executed into an infrastructure dedicated to a stage of development, with specific requirements in terms of clusterized environment where it shall be executed.

For allow distribution of executed test plans during the quality control types need during a CYBNITY Solution versions to evaluate, several customizations of tests execution context are defined and managed via Maven Profiles which can be selected by the tester (or by Continuous Integration pipeline).
```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {
        'background': '#ffffff',
        'fontFamily': 'arial',
        'fontSize': '13px',
        'primaryColor': '#fff',
        'primaryTextColor': '#0e2a43',
        'primaryBorderColor': '#0e2a43',
        'secondaryColor': '#fff',
        'secondaryTextColor': '#0e2a43',
        'secondaryBorderColor': '#fff',
        'tertiaryColor': '#fff',
        'tertiaryTextColor': '#0e2a43',
        'tertiaryBorderColor': '#fff',
        'edgeLabelBackground':'#fff',
        'lineColor': '#0e2a43',
        'titleColor': '#fff',
        'textColor': '#0e2a43',
        'lineColor': '#0e2a43',
        'nodeTextColor': '#0e2a43',
        'nodeBorder': '#0e2a43',
        'noteTextColor': '#fff',
        'noteBorderColor': '#fff'
    },
    'flowchart': { 'curve': 'monotoneX', 'htmlLabels': 'true', 'wrappingWidth': '400' }
  }
}%%
flowchart TD
    e_env("#60;#60;Deployment Context#62;#62; \n Test Execution Environment")
    dev_env_profile("#60;#60;Maven Profile#62;#62; \n DEV Profile") & qa_env_profile("#60;#60;Maven Profile#62;#62; \n QA Profile") & uat_env_profile("#60;#60;Maven Profile#62;#62; \n UAT Profile") & perf_env_profile("#60;#60;Maven Profile#62;#62; \n PERF Profile") -->|Specialized execution as|e_env

    classDef context fill:#fff, stroke:##0e2a43, color:##0e2a43;
    classDef testelement fill:#0e2a43, color:#fff;
    class e_env context
    class dev_env_profile,qa_env_profile,uat_env_profile,perf_env_profile testelement
```
## Test Plans
The quality control of CYBNITY applications and features is structured for allow flexible execution according to many stage of a project, and the quality control plan structure is based on dissimenated scope via test plans.

Each test plan is implemented as an executable Java application component (e.g. JUnit project, using a Maven Profile targeting the condition of execution to apply), defining a set of test scenario and cases to execute, and generating a results report.

Find here an overview of the categorized test plans which are implemented over dedicated sub-projects.

```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {
        'background': '#ffffff',
        'fontFamily': 'arial',
        'fontSize': '13px',
        'primaryColor': '#fff',
        'primaryTextColor': '#0e2a43',
        'primaryBorderColor': '#0e2a43',
        'secondaryColor': '#fff',
        'secondaryTextColor': '#0e2a43',
        'secondaryBorderColor': '#fff',
        'tertiaryColor': '#fff',
        'tertiaryTextColor': '#0e2a43',
        'tertiaryBorderColor': '#fff',
        'edgeLabelBackground':'#fff',
        'lineColor': '#0e2a43',
        'titleColor': '#fff',
        'textColor': '#0e2a43',
        'lineColor': '#0e2a43',
        'nodeTextColor': '#0e2a43',
        'nodeBorder': '#0e2a43',
        'noteTextColor': '#fff',
        'noteBorderColor': '#fff'
    },
    'flowchart': { 'curve': 'monotoneX', 'htmlLabels': 'true', 'wrappingWidth': '400' }
  }
}%%
flowchart LR
  ui_func_plan("UI Module Capabilities Test Plan") -->|extends| functionalPlan
  functionalPlan("Functional Acceptance Criteria")
  technicalPlan("Technical Acceptance Criteria")

  classDef dimension fill:#fff, stroke:##0e2a43, color:##0e2a43;
  classDef testelement fill:#0e2a43, color:#fff;
  classDef component fill:#fff, stroke:#e5302a, color:#e5302a, stroke-dasharray: 5 5;
  
  class functionalPlan,technicalPlan dimension
  class ui_func_plan testelement
```

### Managed Plans
