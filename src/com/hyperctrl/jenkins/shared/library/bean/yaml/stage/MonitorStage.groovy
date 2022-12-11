package com.hyperctrl.jenkins.shared.library.bean.yaml.stage

class MonitorStage {
    Prometheus prometheus

    static class Prometheus {
        boolean enable
    }
}
