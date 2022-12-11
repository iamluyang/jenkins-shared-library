package com.hyperctrl.jenkins.shared.library.stage.plugin.docker


import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.service.api.IDockerService
import com.hyperctrl.jenkins.shared.library.service.impl.DockerService
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.LogUtils
import com.hyperctrl.jenkins.shared.library.utils.StringUtils

@PipelineStage(name = "Docker Image Build Pipeline Stage", stage = StageType.DOCKER_BUILD)
class DefaultDockerBuildPipelineStage implements IPipelineStage {

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
        //String dockerImageTag = script.git.commitHash()
        String dockerImageOwner = script.params.DockerImageOwner
        String dockerImageTag = script.params.DockerImageTag
        String subService = script.params.SubService
        String[] subServiceNames = subService.split(",")

        dockerRepository.login()
        for (String subServiceName : subServiceNames) {
            String dockerImageName = subServiceName.split("/")[1]
            dockerRepository.build(dockerImageOwner, dockerImageName, dockerImageTag, new HashMap<String, String>())
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