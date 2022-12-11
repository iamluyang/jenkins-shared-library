package com.hyperctrl.jenkins.shared.library.bean.yaml

import com.hyperctrl.jenkins.shared.library.bean.yaml.stage.*

class PipelineStages {
    TestingStage test
    DockerStage docker
    DeployStage deploy
    NotifyStage notify
    MonitorStage monitor
}
