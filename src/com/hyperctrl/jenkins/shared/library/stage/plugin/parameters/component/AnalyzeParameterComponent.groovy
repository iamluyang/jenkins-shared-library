package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.type.AnalyzeType
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class AnalyzeParameterComponent implements IParameterComponent {

    def script

    AnalyzeParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Analyze Setting"))


        def analyzeEnable = ParameterUtils.booleanParam(
                script, "AnalyzeEnable", "Enable Analyze", true)

        def analyzeType = ParameterUtils.choiceSingleSelectParam(
                script, "AnalyzeType", "Choosing analysis for your project.",
                AnalyzeType.getDescriptions(), AnalyzeType.getValues())

        component.add(group)
        component.add(analyzeEnable)
        component.add(analyzeType)
    }
}
