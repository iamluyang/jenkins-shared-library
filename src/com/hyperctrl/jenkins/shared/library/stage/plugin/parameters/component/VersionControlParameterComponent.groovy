package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class VersionControlParameterComponent implements IParameterComponent {

    private IParametersService parametersService = new ParametersService()

    def script

    VersionControlParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {
        def group = this.script.separator(ParameterUtils.separator(this.class, "Version Control Setting"))

        def versionControlType = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlType", "Choosing type for your remote repository",
                parametersService.getVersionTypeRepository().getDescriptions(),
                parametersService.getVersionTypeRepository().getValues())

        def versionControlAccount = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlAccount", "Choosing account for your remote repository",
                parametersService.getVersionUserRepository().getDescriptions(),
                parametersService.getVersionUserRepository().getValues())

        def versionControlProject = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlProject", "Choosing project for your remote repository",
                parametersService.getVersionProjectRepository().getDescriptions(),
                parametersService.getVersionProjectRepository().getValues())

        def versionControlBranch = ParameterUtils.choiceSingleSelectParam(
                script, "VersionControlBranch", "Choosing branch for your remote repository",
                parametersService.getVersionBranchRepository().getDescriptions(),
                parametersService.getVersionBranchRepository().getValues())

        def versionControlCredentialsId = ParameterUtils.basicSSHUserPrivateKeyParam(
                script,
                "VersionControlCredentialsId",
                'Choosing a credentialsId for your remote repository',
                "768abe74-f2c4-4e61-b691-afd3ba25f432"
        )

        component.add(group)
        component.add(versionControlType)
        component.add(versionControlAccount)
        component.add(versionControlProject)
        component.add(versionControlBranch)
        component.add(versionControlCredentialsId)
    }
}
