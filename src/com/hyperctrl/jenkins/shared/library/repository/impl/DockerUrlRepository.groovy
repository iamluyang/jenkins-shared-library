package com.hyperctrl.jenkins.shared.library.repository.impl


import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem
import com.hyperctrl.jenkins.shared.library.repository.api.IDockerUrlRepository
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class DockerUrlRepository implements IDockerUrlRepository {

    private List<String> dockerRepositoryUrls = new ArrayList<>()

    def script

    DockerUrlRepository(def script) {
        this.script = script
        dockerRepositoryUrls.add(new ChoiceItem("Github Repository", "ghcr.io"))
        dockerRepositoryUrls.add(new ChoiceItem("Aliyun Repository", "registry.cn-hangzhou.aliyuncs.com"))
        dockerRepositoryUrls.add(new ChoiceItem("Harbor Repository", "192.168.87.204"))
    }

    @Override
    String getDescriptions() {
        return ParameterUtils.getDescriptions(dockerRepositoryUrls)
    }

    @Override
    String getValues() {
        return ParameterUtils.getValues(dockerRepositoryUrls)
    }
}
