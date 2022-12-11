package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component

import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.type.CIType
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class ContinuousIntegrationComponent implements IParameterComponent {

    private IParametersService parametersService = new ParametersService()

    def script

    ContinuousIntegrationComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {
        def group = this.script.separator(ParameterUtils.separator(this.class, "Continuous Integration Setting"))

        def continuousIntegrationType = ParameterUtils.choiceSingleSelectParam(
                script, "ContinuousIntegrationType", "Choosing type for your continuous integration.",
                CIType.getDescriptions(), CIType.getValues())

        component.add(group)
        component.add(continuousIntegrationType)
    }
}
