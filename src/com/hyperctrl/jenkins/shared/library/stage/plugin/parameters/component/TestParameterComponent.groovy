package com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.component


import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.IParameterComponent
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class TestParameterComponent implements IParameterComponent {

    def script

    TestParameterComponent(def script) {
        this.script = script
    }

    @Override
    void build(List component) {

        def group = this.script.separator(ParameterUtils.separator(this.class, "Testing Setting"))

        def unitTestEnable = ParameterUtils.booleanParam(
                script, "UnitTestEnable", "Enable Unit Test", true)

        def integrationTestEnable = ParameterUtils.booleanParam(
                script, "IntegrationTestEnable", "Enable Integration Test", true)

        def healthCheckTestEnable = ParameterUtils.booleanParam(
                script, "HealthCheckTestEnable", "Enable HealthCheck Test", true)

        component.add(group)
        component.add(unitTestEnable)
        component.add(integrationTestEnable)
        component.add(healthCheckTestEnable)
    }
}
