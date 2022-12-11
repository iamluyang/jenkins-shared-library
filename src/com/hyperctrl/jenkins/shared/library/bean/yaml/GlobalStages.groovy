package com.hyperctrl.jenkins.shared.library.bean.yaml

import com.hyperctrl.jenkins.shared.library.bean.yaml.stage.*

class GlobalStages {
    VersionStage version
    ServiceStage service
    TestingStage testing
    DockerStage docker
    DeployStage deploy
    NotifyStage notify
    MonitorStage monitor
}
