package com.hyperctrl.jenkins.shared.library.repository.impl


import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem
import com.hyperctrl.jenkins.shared.library.repository.api.IVersionTypeRepository
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class VersionTypeRepository implements IVersionTypeRepository {

    private List<ChoiceItem> versionControlTypes = new ArrayList<>()

    def script

    VersionTypeRepository(def script) {
        this.script = script
        versionControlTypes.add(new ChoiceItem("GIT Repository", "GIT", ))
        versionControlTypes.add(new ChoiceItem("SVN Repository", "SVN", ))
        versionControlTypes.add(new ChoiceItem("CVS Repository", "CVS", ))
    }

    @Override
    String getDescriptions() {
        return ParameterUtils.getDescriptions(versionControlTypes)
    }

    @Override
    String getValues() {
        return ParameterUtils.getValues(versionControlTypes)
    }
}
