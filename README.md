## PURPOSE
Repository dedicated to the quality control projects (e.g; testing applications implementing test scenario plans), with mission to evaluate and report the quality of CYBNITY software components and systems versions.

You can find informations relative to test plans maintenance like:
- Design diagrams regarding organization of test plans and eventual dependencies
- Support to test plans execution according to execution environments targeted
- Test software developed and maintained as test plans reusable Non-Regression quality control systems

# QUALITY CONTROL DIMENSIONS
The quality control of CYBNITY applications and features is structured for allow flexible execution according to many stage of a project, and the test plans structure is based on dissimenated scope of test types.

Find here an overview of the test elements and categories which are implemented over dedicated projects.
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
subgraph environments[" "]
    direction TD
    e_env("#60;#60;Deployment Context#62;#62; \n Test Execution Environment")
    dev_env_profile("#60;#60;Maven Profile#62;#62; \n DEV Profile") & qa_env_profile("#60;#60;Maven Profile#62;#62; \n QA Profile") & uat_env_profile("#60;#60;Maven Profile#62;#62; \n UAT Profile") & perf_env_profile("#60;#60;Maven Profile#62;#62; \n PERF Profile") -->|Extends|e_env
end
subgraph plans[" "]
    direction LR
    t_case("Test Case") --- t_sc("Test Scenario") --- b_a_c("#60;#60;Quality Control Dimension#62;#62; \n Business Acceptance Criteria")
end

    classDef dimension fill:#fff, stroke:##0e2a43, color:##0e2a43;
    classDef testelement fill:#0e2a43, color:#fff;
    classDef component fill:#fff, stroke:#e5302a, color:#e5302a, stroke-dasharray: 5 5;

    class b_a_c dimension
    class t_case,t_sc,dev_env_profile,qa_env_profile,uat_env_profile,perf_env_profile testelement
```
