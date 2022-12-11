package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component

import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.type.DeployType
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class DeployParameterComponent implements IParameterComponent {

    private IParametersService parametersService = new ParametersService()

    def script

    DeployParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {
        def group = this.script.separator(ParameterUtils.separator(this.class, "Deploy Setting"))

        def deployEnable = ParameterUtils.booleanParam(
                script, "DeployEnable", "Enable Deploy", true)

        def deployType = ParameterUtils.choiceSingleSelectParam(
                script, "DeployType", "Choosing a type for your deployment",
                DeployType.getDescriptions(), DeployType.getValues())

        component.add(group)
        component.add(deployEnable)
        component.add(deployType)
    }
}
