package com.hyperctrl.jenkins.shared.library.stage.plugin.artifact

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.service.api.IMavenService
import com.hyperctrl.jenkins.shared.library.service.impl.MavenService
import com.hyperctrl.jenkins.shared.library.type.CIType
import com.hyperctrl.jenkins.shared.library.type.StageType

@PipelineStage(name = "JavaWithMaven Package Artifact Push Pipeline Stage", stage = StageType.ARTIFACT_DEPLOY)
class JavaWithMavenArtifactDeployPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return CIType.JAVA_WITH_MAVEN.name()
    }

    @Override
    boolean can(def script) {
        return script.params.ArtifactDeployEnable
    }

    @Override
    def execute(def script) {
        IMavenService mavenService = new MavenService(script)
        mavenService.cleanAndDeploy()
    }
}
