package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class JiraParameterComponent implements IParameterComponent {

    def script

    JiraParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Jira Setting"))

        def jira = ParameterUtils.stringParam(script, "Jira", "JIRA ID", "")

        component.add(group)
        component.add(jira)
    }
}
