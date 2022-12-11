package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component

import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class ServiceParameterComponent implements IParameterComponent {

    private IParametersService parametersService

    def script

    ServiceParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {
        parametersService = new ParametersService(script)

        def group = this.script.separator(ParameterUtils.separator(this.class, "SubServices Setting"))
        def subService = ParameterUtils.choiceCheckboxParam(
                script, "SubService",
                "Choosing sub services for a project",
                parametersService.getServiceRepository().getDescriptions(),
                parametersService.getServiceRepository().getValues())

        component.add(group)
        component.add(subService)
    }
}
