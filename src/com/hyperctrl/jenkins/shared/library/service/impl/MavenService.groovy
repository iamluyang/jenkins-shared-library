package com.hyperctrl.jenkins.shared.library.service.impl


import com.hyperctrl.jenkins.shared.library.service.api.IMavenService

class MavenService implements IMavenService {

    def script

    def getScript() {
        return script
    }

    MavenService(def script) {
        this.script = script
    }

    @Override
    void cleanAndPackage() {
        script.sh "mvn clean package"
    }

    @Override
    void cleanAndDeploy() {
        String mavenCredentialsId = this.script.params.MavenCredentialsId
        this.script {
            withCredentials([usernamePassword(credentialsId: "${mavenCredentialsId}", passwordVariable: 'mavenPassword', usernameVariable: 'mavenUsername')])
            {
                sh "mvn clean deploy -Dmaven.test.skip=true -Dserver.username=${mavenUsername} -Dserver.password=${mavenPassword}"
            }
        }
    }
}
