package com.hyperctrl.jenkins.shared.library.bean.yaml.stage

class DeployStage {
    String type
    K8s k8s

    // --------------------------------------------------
    // K8s
    // --------------------------------------------------
    static class K8s {
        Replicas replicas
        Resources resources
        Ingress ingress
    }

    static class Replicas {
        int pod = 1
    }

    static class Resources {
        Limits limits
    }

    static class Limits {
        String cpu
        String memory
    }

    static class Ingress {
        String host
        String serviceName
        String servicePort
    }
}

