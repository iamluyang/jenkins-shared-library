package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class ConfluenceParameterComponent implements IParameterComponent {

    def script

    ConfluenceParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Confluence Setting"))

        def confluenceId = ParameterUtils.stringParam(script, "ConfluenceId", "Confluence ID", "")

        component.add(group)
        component.add(confluenceId)
    }
}
