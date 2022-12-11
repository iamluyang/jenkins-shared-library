package com.hyperctrl.jenkins.shared.library.stage.plugin.docker

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.service.api.IDockerService
import com.hyperctrl.jenkins.shared.library.service.impl.DockerService
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.LogUtils
import com.hyperctrl.jenkins.shared.library.utils.StringUtils

@PipelineStage(name = "Docker Image Push Pipeline Stage", stage = StageType.DOCKER_PUSH)
class GithubDockerPushPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return StringUtils.NONE
    }

    @Override
    boolean can(def script) {
        return script.params.DockerPushEnable && script.params.ArtifactDeployEnable
    }

    @Override
    def execute(def script) {
        IDockerService dockerRepository = createDockerRepository(script)
        String dockerImageOwner = script.params.DockerImageOwner
        String dockerImageTag = script.params.DockerImageTag
        String subService = script.params.SubService
        String[] subServiceNames = subService.split(",")

        dockerRepository.login()
        for (String subServiceName : subServiceNames) {
            dockerRepository.push(dockerImageOwner, subServiceName, dockerImageTag)
            dockerRepository.rmi(dockerImageOwner, subServiceName, dockerImageTag)
            dockerRepository.imagePrune()
        }
        LogUtils.log(script, "List docker images after build image action")
        dockerRepository.images()
    }

    private IDockerService createDockerRepository(def script) {
        String dockerRegistryUrl = script.params.DockerRegistryUrl
        dockerRegistryUrl = StringUtils.trimEndForwardSlash(dockerRegistryUrl)
        IDockerService dockerRepository = new DockerService(script, dockerRegistryUrl)
        return dockerRepository
    }
}
