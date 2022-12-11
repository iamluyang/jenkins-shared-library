package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component

import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.type.DomainType
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class DomainParameterComponent implements IParameterComponent {

    private IParametersService parametersService = new ParametersService()

    def script

    DomainParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Domain Name Setting"))

        def domainNameEnable = ParameterUtils.booleanParam(
                script, "DomainNameEnable", "Enable Domain Name", true)

        def domainNameType = ParameterUtils.choiceSingleSelectParam(
                script, "DomainNameType", "Choosing a type for your deployment",
                DomainType.getDescriptions(), DomainType.getValues())

        def domainNameCredentialsId = ParameterUtils.usernamePasswordCredentialsParam(
                script,
                "DomainNameCredentialsId",
                'Choosing credentialsId for your domain name server',
                "5f021ff5-8054-455c-8747-fb42b0b83674")

        component.add(group)
        component.add(domainNameEnable)
        component.add(domainNameType)
        component.add(domainNameCredentialsId)
    }
}
