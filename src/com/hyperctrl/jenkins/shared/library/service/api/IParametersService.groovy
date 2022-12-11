package com.hyperctrl.jenkins.shared.library.service.api

import com.hyperctrl.jenkins.shared.library.repository.api.*

interface IParametersService {

    IVersionTypeRepository getVersionTypeRepository()

    IVersionUserRepository getVersionUserRepository()

    IVersionProjectRepository getVersionProjectRepository();

    IVersionBranchRepository getVersionBranchRepository();

    IServiceRepository getServiceRepository();

    IDockerUrlRepository getDockerUrlRepository()

}
