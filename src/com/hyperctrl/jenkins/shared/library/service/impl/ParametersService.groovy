package com.hyperctrl.jenkins.shared.library.service.impl

import com.hyperctrl.jenkins.shared.library.repository.api.*
import com.hyperctrl.jenkins.shared.library.repository.impl.*
import com.hyperctrl.jenkins.shared.library.service.api.IParametersService

class ParametersService implements IParametersService {

    def script

    private IExtendedChoice versionTypeRepository

    private IExtendedChoice versionUserRepository

    private IExtendedChoice versionProjectRepository

    private IExtendedChoice versionBranchRepository

    private IExtendedChoice serviceRepository

    private IExtendedChoice dockerUrlRepository

    ParametersService(def script) {
        this.script = script
        versionTypeRepository = new VersionTypeRepository(script)
        versionUserRepository = new VersionAccountRepository(script)
        versionProjectRepository = new VersionProjectRepository(script)
        versionBranchRepository = new VersionBranchRepository(script)
        serviceRepository = new ServiceRepository(script)
        dockerUrlRepository = new DockerUrlRepository(script)
    }

    @Override
    IVersionTypeRepository getVersionTypeRepository() {
        return versionTypeRepository
    }

    @Override
    IVersionUserRepository getVersionUserRepository() {
        return versionUserRepository
    }

    @Override
    IVersionProjectRepository getVersionProjectRepository() {
        return versionProjectRepository
    }

    @Override
    IVersionBranchRepository getVersionBranchRepository() {
        return versionBranchRepository
    }

    @Override
    IServiceRepository getServiceRepository() {
        return serviceRepository
    }

    @Override
    IDockerUrlRepository getDockerUrlRepository() {
        return dockerUrlRepository
    }
}
