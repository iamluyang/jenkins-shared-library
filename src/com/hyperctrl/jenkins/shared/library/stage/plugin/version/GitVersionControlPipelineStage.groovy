package com.hyperctrl.jenkins.shared.library.stage.plugin.version

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.type.VCType

@PipelineStage(name = "Git Version Control Pipeline Stage", stage = StageType.VERSION)
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
