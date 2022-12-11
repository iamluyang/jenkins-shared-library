package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class ArtifactParameterComponent implements IParameterComponent {

    public def script

    ArtifactParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Artifact Setting"))

        def artifactDeployEnable = ParameterUtils.booleanParam(
                script, "ArtifactDeployEnable", "Enable Artifact Publish", true)

        def artifactCredentialsId = ParameterUtils.usernamePasswordCredentialsParam(
                script,
                "ArtifactCredentialsId",
                'Choosing credentialsId for your remote maven registry.',
                "b0c2b37a-3d08-4999-8b11-52ddfcaddaf4"
        )

        component.add(group)
        component.add(artifactDeployEnable)
        component.add(artifactCredentialsId)
    }
}
