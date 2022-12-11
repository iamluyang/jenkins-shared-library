package com.hyperctrl.jenkins.shared.library.service.impl

import com.hyperctrl.jenkins.shared.library.service.api.IDockerService

class DockerService implements IDockerService {

    def script

    def getScript() {
        return script
    }

    String dockerRegistryUrl

    DockerService(def script, String dockerRegistryUrl) {
        this.script = script
        this.dockerRegistryUrl = dockerRegistryUrl
    }

    @Override
    void login() {
        String dockerCredentialsId = script.params.DockerCredentialsId

        this.script {
            withCredentials([usernamePassword(credentialsId: "${dockerCredentialsId}", passwordVariable: 'password', usernameVariable: 'username')])
            {
                sh "docker login ${dockerRegistryUrl} -u ${username} -p ${password}"
            }
        }
    }

    @Override
    void build(String dockerImageOwner, String dockerImageName, String dockerImageTag, Map<String, String> dockerImageLabels) {
        String cmd = "docker build -t ${dockerRegistryUrl}/${dockerImageOwner}/${dockerImageName}:${dockerImageTag}"
        if (dockerImageLabels != null) {
            for (Map.Entry<String, String> dockerImageLabel : dockerImageLabels.entrySet()) {
                cmd.concat(" --label \"${dockerImageLabel.key = dockerImageLabel.value}\" ")
            }
        }
        cmd = cmd.concat(" ${dockerImageName}")
        script.sh cmd
    }

    @Override
    void push(String dockerImageOwner, String dockerImageName, String dockerImageTag) {
        script.sh "docker push ${dockerRegistryUrl}/${dockerImageOwner}/${dockerImageName}:${dockerImageTag}"
    }

    @Override
    void pushBySha(String dockerImageOwner, String dockerImageName, String sha) {
        script.sh "docker push ${dockerRegistryUrl}/${dockerImageOwner}/${dockerImageName}@${sha}"
    }

    @Override
    void rmi(String dockerImageOwner, String dockerImageName, String dockerImageTag) {
        script.sh "docker rmi ${dockerRegistryUrl}/${dockerImageOwner}/${dockerImageName}:${dockerImageTag}"
    }

    @Override
    void imagePrune() {
        script.sh "docker image prune --force"
    }

    @Override
    void inspect(String dockerImageOwner, String dockerImageName, String dockerImageTag) {
        script.sh "docker inspect ${dockerRegistryUrl}/${dockerImageOwner}/${dockerImageName}:${dockerImageTag}"
    }

    @Override
    void images() {
        script.sh "docker images"
    }
}
