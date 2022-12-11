package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.service.api.IParametersService
import com.hyperctrl.jenkins.shared.library.service.impl.ParametersService
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component.*
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.LogUtils
import com.hyperctrl.jenkins.shared.library.utils.StringUtils

@PipelineStage(name = "Parameters Pipeline Stage", stage = StageType.PARAMETERS)
class ParametersPipelineStage implements IPipelineStage {

    private IParametersService parametersService = new ParametersService()

    def script;

    def getScript() {
        return script
    }

    @Override
    String getType() {
        return StringUtils.NONE
    }

    @Override
    boolean can(def script) {
        return true
    }

    @Override
    def execute(def script) {
        this.script = script
        LogUtils.log(script, "Please input pipeline parameters.")
        List component = new ArrayList()

        new ContinuousIntegrationComponent(script).build(component)
        new VersionControlParameterComponent(script).build(component)
        new ServiceParameterComponent(script).build(component)
        new TestParameterComponent(script).build(component)
        new AnalyzeParameterComponent(script).build(component)
        new ArtifactParameterComponent(script).build(component)
        new DockerParameterComponent(script).build(component)
        new DeployParameterComponent(script).build(component)
        new DomainParameterComponent(script).build(component)
        new NotifyParameterComponent(script).build(component)
        new JiraParameterComponent(script).build(component)
        new ConfluenceParameterComponent(script).build(component)
        new MonitorParameterComponent(script).build(component)
        return [
                script.parameters(component)
        ]

    }
}
