import com.hyperctrl.jenkins.shared.library.pipline.impl.DefaultJenkinsPipeline
import com.hyperctrl.jenkins.shared.library.stage.plugin.analyze.SonarQubeAnalyzeCodePipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.artifact.JavaWithMavenArtifactBuildPaPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.artifact.JavaWithMavenArtifactDeployPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.deploy.DefaultDeployPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.docker.DefaultDockerBuildPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.docker.GithubDockerPushPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.domain.AliyunDomainPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.monitor.PrometheusMonitorPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.notify.DefaultNotifyPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.ParametersPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.testing.JavaWithMavenHealthCheckTestingPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.testing.JavaWithMavenIntegrationTestingPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.testing.JavaWithMavenUnitTestingPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.version.GitVersionControlPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.welcome.WelcomePipelineStage
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.YamlUtils

def call(Map args = [:], Closure body = {}) {

    def jenkinsPipeline = new DefaultJenkinsPipeline()
    jenkinsPipeline.addPipeStage(new WelcomePipelineStage())
    jenkinsPipeline.addPipeStage(new ParametersPipelineStage())
    jenkinsPipeline.addPipeStage(new GitVersionControlPipelineStage())

    jenkinsPipeline.addPipeStage(new JavaWithMavenArtifactBuildPaPipelineStage())
    jenkinsPipeline.addPipeStage(new JavaWithMavenArtifactDeployPipelineStage())
    jenkinsPipeline.addPipeStage(new JavaWithMavenUnitTestingPipelineStage())

    jenkinsPipeline.addPipeStage(new DefaultDockerBuildPipelineStage())
    jenkinsPipeline.addPipeStage(new GithubDockerPushPipelineStage())
    jenkinsPipeline.addPipeStage(new SonarQubeAnalyzeCodePipelineStage())
    jenkinsPipeline.addPipeStage(new DefaultNotifyPipelineStage())

    jenkinsPipeline.addPipeStage(new AliyunDomainPipelineStage())
    jenkinsPipeline.addPipeStage(new DefaultDeployPipelineStage())  // TODO
    jenkinsPipeline.addPipeStage(new JavaWithMavenHealthCheckTestingPipelineStage()) // TODO
    jenkinsPipeline.addPipeStage(new JavaWithMavenIntegrationTestingPipelineStage()) // TODO
    jenkinsPipeline.addPipeStage(new JavaWithMavenUnitTestingPipelineStage()) // TODO
    jenkinsPipeline.addPipeStage(new PrometheusMonitorPipelineStage())  // TODO

    pipeline {

        agent any

        stages {
            stage('0.Welcome') {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.WELCOME)
                        jenkinsPipeline.execute(this, StageType.NOTIFY, params.NotifyType)
                    }
                }
            }

            stage('1.Setup') {
                steps {
                    script {
                        properties(jenkinsPipeline.execute(this, StageType.PARAMETERS))
                    }
                }
            }

            stage("2.Checkout") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.VERSION, params.CheckoutRepositoryType)
                        YamlUtils.load(this)
                    }
                }
            }

            stage("3.Package Artifact") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.ARTIFACT_BUILD, params.ContinuousIntegrationType)
                    }
                }
            }

            stage("4.Unit Test") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.UNIT_TESTING, params.ContinuousIntegrationType)
                    }
                }
            }

            stage("5.Package Publish") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.ARTIFACT_DEPLOY, params.ContinuousIntegrationType)
                    }
                }
            }

            stage("6.Code Analysis") {
                steps{
                    script {
                        jenkinsPipeline.execute(this, StageType.ANALYZE, params.AnalyzeType)
                    }
                }
            }

            stage("7.Push Docker Image") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.DOCKER_BUILD)
                        jenkinsPipeline.execute(this, StageType.DOCKER_PUSH)
                    }
                }
            }

            stage("8.Deployment") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.DEPLOY)
                    }
                }
            }

            stage("9.Add Domain Name") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.DOMAIN)
                    }
                }
            }

            stage("10.Send Notification") {
                steps {
                    script {
                        jenkinsPipeline.execute(this, StageType.NOTIFY)
                    }
                }
            }
        }
    }
}
