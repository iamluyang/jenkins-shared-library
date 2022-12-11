package com.hyperctrl.jenkins.shared.library.stage.plugin.report

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.ReportType
import com.hyperctrl.jenkins.shared.library.type.StageType

@PipelineStage(name = "Unit Test Report Pipeline Stage", stage = StageType.REPORT)
class UnitTestReportPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return ReportType.UNIT_TEST.name()
    }

    @Override
    boolean can(def script) {
        return true
    }

    @Override
    def execute(def script) {

    }
}