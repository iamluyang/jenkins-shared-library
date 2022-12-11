package com.hyperctrl.jenkins.shared.library.stage.plugin.monitor

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.MonitorType
import com.hyperctrl.jenkins.shared.library.type.StageType

@PipelineStage(name = "Prometheus Monitor Pipeline Stage", stage = StageType.MONITOR)
class PrometheusMonitorPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return MonitorType.Prometheus.name()
    }

    @Override
    boolean can(def script) {
        return script.params.PrometheusEnable
    }

    @Override
    def execute(def script) {

    }
}