package com.hyperctrl.jenkins.shared.library.repository.impl


import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem
import com.hyperctrl.jenkins.shared.library.repository.api.IVersionUserRepository
import com.hyperctrl.jenkins.shared.library.utils.ParameterUtils

class VersionAccountRepository implements IVersionUserRepository {

    private List<ChoiceItem> versionControlAccounts = new ArrayList<>()

    def script

    VersionAccountRepository(def script) {
        this.script = script
        versionControlAccounts.add(new ChoiceItem("git@github.com:iamluyang", "git@github.com:iamluyang"))
    }

    @Override
    String getDescriptions() {
        return ParameterUtils.getDescriptions(versionControlAccounts)
    }

    @Override
    String getValues() {
        return ParameterUtils.getValues(versionControlAccounts)
    }
}
