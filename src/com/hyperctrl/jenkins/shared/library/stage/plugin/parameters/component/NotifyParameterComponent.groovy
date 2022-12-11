package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component

import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.type.NotifyType
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class NotifyParameterComponent implements IParameterComponent {

    def script

    NotifyParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Notify Setting"))

        def notificationType = ParameterUtils.choiceCheckboxParam(
                script, "NotifyType", "Notify Setting",
                NotifyType.getDescriptions(), NotifyType.getValues())


        def smtpCredentialsId = ParameterUtils.usernamePasswordCredentialsParam(
                script,
                "SmtpCredentialsId",
                'Choosing credentialsId for your smtp account',
                "0066e942-f193-4da1-9c51-a4ea1560e630"
        )

        component.add(group)
        component.add(notificationType)
        component.add(smtpCredentialsId)
    }
}
