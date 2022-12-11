package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class DockerParameterComponent implements IParameterComponent {

    private IParametersService parametersService = new ParametersService()

    def script

    DockerParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Docker Setting"))

        def dockerPushEnable = ParameterUtils.booleanParam(
                script, "DockerPushEnable", "Enable Docker Push", true)

        def dockerCredentialsId = ParameterUtils.usernamePasswordCredentialsParam(
                script,
                "DockerCredentialsId",
                'Choosing credentialsId for your remote docker registry',
                "41541041-322c-456a-a9cb-5a93d240ff1f"
        )

        def dockerRegistryUrl = ParameterUtils.choiceSingleSelectParam(
                script, "DockerRegistryUrl", "Publish Docker Image to remote repository.",
                parametersService.getDockerUrlRepository().getDescriptions(),
                parametersService.getDockerUrlRepository().getValues())

        def dockerImageOwner = ParameterUtils.stringParam(
                script, "DockerImageOwner", "Set Docker Image Owner.", "iamluyang")

        def dockerImageTag = ParameterUtils.stringParam(
                script, "DockerImageTag", "Set Docker Image Tag.", "latest")

        component.add(group)
        component.add(dockerPushEnable)
        component.add(dockerRegistryUrl)
        component.add(dockerCredentialsId)
        component.add(dockerImageOwner)
        component.add(dockerImageTag)
    }
}
