package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class MonitorParameterComponent implements IParameterComponent {

    def script

    MonitorParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Monitor Setting"))

        def prometheusEnable = ParameterUtils.booleanParam(
                script, "PrometheusEnable", "Enable Prometheus monitor.", true)

        component.add(group)
        component.add(prometheusEnable)
    }
}
