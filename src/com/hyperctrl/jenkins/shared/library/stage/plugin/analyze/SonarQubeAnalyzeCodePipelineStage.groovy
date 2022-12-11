package com.hyperctrl.jenkins.shared.library.stage.plugin.analyze

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.AnalyzeType
import com.hyperctrl.jenkins.shared.library.type.StageType

@PipelineStage(name = "SonarQube Analyze Pipeline Stage", stage = StageType.ANALYZE)
class SonarQubeAnalyzeCodePipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return AnalyzeType.SONARQUBE.name()
    }

    @Override
    boolean can(def script) {
        return script.params.AnalyzeEnable
    }

    @Override
    def execute(def script) {
        def scannerHome = script.tool 'sonar-scanner'
        script.withSonarQubeEnv('sonar-qube-server') {
            script.sh "${scannerHome}/bin/sonar-scanner"
        }
    }
}
