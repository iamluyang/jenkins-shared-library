package com.hyperctrl.jenkins.shared.library.stage.plugin.testing

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.CIType
import com.hyperctrl.jenkins.shared.library.type.StageType

@PipelineStage(name = "JavaWithMaven Unit Test Pipeline Stage", stage = StageType.UNIT_TESTING)
class JavaWithMavenUnitTestingPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return CIType.JAVA_WITH_MAVEN.name()
    }

    @Override
    boolean can(def script) {
        return script.params.UnitTestEnable
    }

    @Override
    def execute(def script) {
        script.sh "mvn test"
    }
}
