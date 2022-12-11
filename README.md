# Readme
* This is an experimental project, my first time writing an automated deployment program for Jenkins Devops. You can design your pipeline in OOP way

![yuque_diagram (1)](https://user-images.githubusercontent.com/10556033/206784739-dc12beb9-95e2-41a1-8de9-a4ad710e68a0.png)

# pipeline.yaml eample

* pipeline.yaml(app) > pipeline.yaml(global) > pipeline.yaml(jenkins-shared-library) > Jenkins UI parameters

```yaml
jenkins:
  groupId: your-app-group-id
  global:
    stages:
      monitor:
        prometheus:
          enable: true
      notify:
        teams:
           webhook: "https://xxx"
        slack:
          webhook: "https://xxx"
        dingtalk:
       slack:
          webhook: "https://xxx"
        email:
          smtpHost: smtp.xxx.com
          smtpPort: 587
          from: aaa@email.com
          to: bbb@email.com,ccc@email.com
  apps:
    - app:
        enable: true
        root: app-parent-directory
        name: app-name1
        pipeline:
          stages:
            test:
              health:
                - /v1/checkMe1
                - /v1/checkMe2
            docker:
              from: jdk11
            deploy:
              type: aws
              k8s:
                replicas:
                  prod: 1
                resources:
                  limits:
                    memory: 8Gi
                ingress:
                  host: app1.host.com
                  serviceName: app-name1
                  servicePort: 80
    - app:
        enable: true
        root: app-parent-directory
        name: app-name2
        pipeline:
          stages:
            test:
              health:
                - /v1/checkMe1
                - /v1/checkMe2
            docker:
              from: jdk11
            deploy:
              type: aws
              k8s:
                replicas:
                  prod: 1
                resources:
                  limits:
                    memory: 8Gi
                ingress:
                  host: app2.host.com
                  serviceName: app-name2
                  servicePort: 80
```

# Jenkins Plugins

* Active Choices Plug-in
* Extended Choice Parameter
* Parameter Separator Plugin

# Parameter Components

![image](https://user-images.githubusercontent.com/10556033/206868875-fed793db-90b2-4c29-ac78-64ac9b448881.png)

* CheckoutParameterComponent

![image](https://user-images.githubusercontent.com/10556033/205495142-5dd63fdb-8594-4a15-833a-4966cfb38213.png)

```java
class VersionControlParameterComponent implements IParameterComponent {

    private IParametersService parametersService = new ParametersService()

    def script

    VersionControlParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {
        def group = this.script.separator(ParameterUtils.separator(this.class, "Version Control Setting"))

        def versionControlType = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlType", "Choosing type for your remote repository",
                parametersService.getVersionControlTypeRepository().getDescriptions(),
                parametersService.getVersionControlTypeRepository().getValues())

        def versionControlAccount = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlAccount", "Choosing account for your remote repository",
                parametersService.getVersionControlUserRepository().getDescriptions(),
                parametersService.getVersionControlUserRepository().getValues())

        def versionControlProject = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlProject", "Choosing project for your remote repository",
                parametersService.getVersionControlProjectRepository().getDescriptions(),
                parametersService.getVersionControlProjectRepository().getValues())

        def versionControlBranch = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlBranch", "Choosing branch for your remote repository",
                parametersService.getVersionControlBranchRepository().getDescriptions(),
                parametersService.getVersionControlBranchRepository().getValues())

        def versionControlCredentialsId = ParameterUtils.basicSSHUserPrivateKeyParam(
                script,
                "VersionControlCredentialsId",
                'Choosing a credentialsId for your remote repository',
                "768abe74-f2c4-4e61-b691-afd3ba25f432"
        )

        component.add(group)
        component.add(versionControlType)
        component.add(versionControlAccount)
        component.add(versionControlProject)
        component.add(versionControlBranch)
        component.add(versionControlCredentialsId)
    }
}
```

* ContinuousIntegrationComponent

![image](https://user-images.githubusercontent.com/10556033/206868447-a43346bb-6238-4cdf-9127-91954591ef12.png)

* VersionControlParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868462-b2e983c0-58b5-4a4d-8fb4-75a72f10ee7e.png)

* ServiceParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868472-d5400b53-e25e-4d2a-b20c-f64033b21809.png)

* TestParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868477-3d45f8e8-04d2-47ad-b190-b4adf9d07102.png)

* AnalyzeParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868488-629ba2b7-4b67-4eda-a92b-bfca5815407e.png)

* ArtifactParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868505-a41ac05f-f68b-49a8-b793-8e687c652bad.png)

* DockerParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868512-bfbeb182-9d48-4955-a453-67405ad0f4c1.png)

* DeployParameterComponent(TODO)

![image](https://user-images.githubusercontent.com/10556033/206868610-73edcead-90ef-4deb-b4c4-ea97e071c0c7.png)

* DomainParameterComponent

* NotifyParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868575-e15f015b-85f8-4e81-bed2-332135995ca9.png)

* JiraParameterComponent

![image](https://user-images.githubusercontent.com/10556033/206868630-105aaf45-ea62-4445-a4cf-48b49233f703.png)

* ConfluenceParameterComponent
![image](https://user-images.githubusercontent.com/10556033/206868636-31ca4715-c021-4051-9b62-452ab903bdfd.png)

* MonitorParameterComponent(TODO)

![image](https://user-images.githubusercontent.com/10556033/206868660-4389ad5d-70e4-4eef-a0f0-e8ffe0b0287c.png)

# Stage Plugins

![image](https://user-images.githubusercontent.com/10556033/206868924-935c13c5-7d89-4c3b-b6f3-6126ef386c74.png)

* GitVersionControlPipelineStage

```java
@PipelineStage(name = "Git Version Control Pipeline Stage", stage = StageType.VERSION_CONTROL)
class GitVersionControlPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return VCType.GIT.name()
    }

    @Override
    boolean can(def script) {
        return true
    }

    @Override
    def execute(def script) {
        script.cleanWs()
        String versionControlUser = script.params.VersionControlUser
        String versionControlProject = script.params.VersionControlProject
        String versionControlBranch = script.params.VersionControlBranch
        String versionControlCredentialsId = script.params.VersionControlCredentialsId
        script.git branch: versionControlBranch, credentialsId: versionControlCredentialsId, url: "${versionControlUser}/${versionControlProject}"
    }
}
```

* DingTalkNotifyPipelineStage
* EmailNotifyPipelineStage
* SlackNotifyPipelineStage
* TeamsNotifyPipelineStage
* PrometheusMonitorPipelineStage
* AliyunDomainPipelineStage
* GithubDockerPushPipelineStage
* AliyunDockerPushPipelineStage
* DefaultDockerBuildPipelineStage
* DefaultDeployPipelineStage
* JavaWithMavenArtifactDeployPipelineStage
* JavaWithMavenArtifactBuildPaPipelineStage
* SonarQubeAnalyzeCodePipelineStage
* ParametersPipelineStage
* UnitTestReportPipelineStage
* JavaWithMavenHealthCheckTestPipelineStage
* JavaWithMavenUnitTestPipelineStage
* JavaWithMavenIntegrationTestPipelineStage
* GitVersionControlPipelineStage
* WelcomePipelineStage

# How to run

## Jenkins Plugin

* [Parameter Separator](https://plugins.jenkins.io/parameter-separator/)

## Create Global Pipeline Libraries

```shell
Dashboard > Manage Jenkins > Configure System > Global Pipeline Libraries
```

* Library Name: jenkins-shared-library
* Default version: master
* SCM
  * git@github.com:iamluyang/hyperctrl-jenkins-library.git
  * your-credentials

![Global Pipeline Libraries](https://user-images.githubusercontent.com/10556033/204347521-e63db3ce-6998-42bb-ab2f-2b8c86609a53.png)

## Create a pipeline Jenkins Project

* Set Pipeline script from SCM
  * your-java-project-git-repository-url
  * your-java-project-git-credentials
  * Script Path: Jenkinsfile
  
![Pipeline-config](https://user-images.githubusercontent.com/10556033/204347477-74919b2c-1ea5-49fa-9d22-53cf7320aab7.png)

## Add Jenkins file to your java-project root folder

* Jenkinsfile

```shell
@Library('jenkins-shared-library@master') _
buildApp()
```
![Jenkins](https://user-images.githubusercontent.com/10556033/204347958-0a16b3b8-bab9-4521-b37b-3ef2fe406efb.png)

## Run a Pipline

![image](https://user-images.githubusercontent.com/10556033/205497051-6c06d41d-204d-49aa-bafd-6dd6fdb42c0d.png)

