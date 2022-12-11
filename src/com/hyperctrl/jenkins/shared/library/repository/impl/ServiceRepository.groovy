package com.hyperctrl.jenkins.shared.library.repository.impl


import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem
import com.hyperctrl.jenkins.shared.library.repository.api.IServiceRepository
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class ServiceRepository implements IServiceRepository {

    private List<String> versionControlServices = new ArrayList<>()

    def script

    ServiceRepository(def script) {
        this.script = script

        versionControlServices.add(new ChoiceItem(
                        "hyperctrl-okra-apps/hyperctrl-okra-apps-server-monitor",
                        "hyperctrl-okra-apps/hyperctrl-okra-apps-server-monitor"))

        versionControlServices.add(new ChoiceItem(
                "hyperctrl-okra-apps/hyperctrl-okra-apps-business-admin",
                "hyperctrl-okra-apps/hyperctrl-okra-apps-business-admin"))

        versionControlServices.add(new ChoiceItem(
                "hyperctrl-okra-apps/hyperctrl-okra-apps-business-board",
                "hyperctrl-okra-apps/hyperctrl-okra-apps-business-board"))
    }

    @Override
    String getDescriptions() {
        return ParameterUtils.getDescriptions(versionControlServices)
    }

    @Override
    String getValues() {
        return ParameterUtils.getValues(versionControlServices)
    }
}
