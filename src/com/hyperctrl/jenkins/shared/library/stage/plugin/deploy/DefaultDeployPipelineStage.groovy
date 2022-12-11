package com.hyperctrl.jenkins.shared.library.stage.plugin.deploy

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.StringUtils

@PipelineStage(name = "Default Deploy Pipeline Stage", stage = StageType.DEPLOY)
class DefaultDeployPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return StringUtils.NONE
    }

    @Override
    boolean can(def script) {
        return script.params.DeployEnable
    }

    @Override
    def execute(def script) {

    }
}
