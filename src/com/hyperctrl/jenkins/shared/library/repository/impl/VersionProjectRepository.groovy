package com.hyperctrl.jenkins.shared.library.repository.impl


import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem
import com.hyperctrl.jenkins.shared.library.repository.api.IVersionProjectRepository
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class VersionProjectRepository implements IVersionProjectRepository {

    private List<String> versionControlProjects = new ArrayList<>()

    def script

    VersionProjectRepository(def script) {
        this.script = script
        versionControlProjects.add(new ChoiceItem("hyperctrl-okra", "hyperctrl-okra"))
    }

    @Override
    String getDescriptions() {
        return ParameterUtils.getDescriptions(versionControlProjects)
    }

    @Override
    String getValues() {
        return ParameterUtils.getValues(versionControlProjects)
    }
}
