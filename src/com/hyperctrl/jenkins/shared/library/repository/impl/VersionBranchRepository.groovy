package com.hyperctrl.jenkins.shared.library.repository.impl


import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem
import com.hyperctrl.jenkins.shared.library.repository.api.IVersionBranchRepository
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class VersionBranchRepository implements IVersionBranchRepository {

    private List<String> versionControlBranches = new ArrayList<>()

    def script

    VersionBranchRepository(def script) {
        this.script = script
        versionControlBranches.add(new ChoiceItem("master", "master"))
        versionControlBranches.add(new ChoiceItem("dev", "dev"))
        versionControlBranches.add(new ChoiceItem("test", "test"))
        versionControlBranches.add(new ChoiceItem("demo", "demo"))
        versionControlBranches.add(new ChoiceItem("prep", "prep"))
    }


    @Override
    String getDescriptions() {
        return ParameterUtils.getDescriptions(versionControlBranches)
    }

    @Override
    String getValues() {
        return ParameterUtils.getValues(versionControlBranches)
    }
}
