package com.hyperctrl.jenkins.shared.library.utils

import hudson.model.Job

class JenkinsUtils {

    private JenkinsUtils() {
        Job
    }

    static def getShortDescriptionOfgetBuildCauses(def script) {
        return script.currentBuild.getBuildCauses()[0].shortDescription
    }

    static def getUserIdOfgetBuildCauses(def script) {
        return script.currentBuild.getBuildCauses()[0].userId
    }

    static def getDurationString(def script) {
        return script.currentBuild.durationString
    }

    static def getCurrentResult(def script) {
        return script.currentBuild.currentResult
    }

    static def getCurrentBuild (def script) {
        return script.currentBuild
    }

    static def getBuildId(def script) {
        return script.BUILD_ID
    }

    static def getBuildNumber(def script) {
        return script.BUILD_NUMBER
    }

    static def getBuildTag(def script) {
        return script.BUILD_TAG
    }

    static def getBuildUrl(def script) {
        return script.BUILD_URL
    }

    static def getExecutorNumber(def script) {
        return script.EXECUTOR_NUMBER
    }

    static def getJavaHome(def script) {
        return script.JAVA_HOME
    }

    static def getJenkinsUrl(def script) {
        return script.JENKINS_URL
    }

    static def getJobName(def script) {
        return script.JOB_NAME
    }

    static def getNodeName(def script) {
        return script.NODE_NAME
    }

    static def getWorkspace(def script) {
        return script.WORKSPACE
    }

    static def envs(def script) {
        script.echo "#=================================================="
        script.echo "BUILD_ID:${script.BUILD_ID}"
        script.echo "BUILD_NUMBER:${script.BUILD_NUMBER}"
        script.echo "BUILD_TAG:${script.BUILD_TAG}"
        script.echo "BUILD_URL:${script.BUILD_URL}"
        script.echo "EXECUTOR_NUMBER:${script.EXECUTOR_NUMBER}"
        script.echo "JAVA_HOME:${script.JAVA_HOME}"
        script.echo "JENKINS_URL:${script.JENKINS_URL}"
        script.echo "JOB_NAME:${script.JOB_NAME}"
        script.echo "NODE_NAME:${script.NODE_NAME}"
        script.echo "WORKSPACE:${script.WORKSPACE}"
        script.echo "#=================================================="
    }

    static def pwd(def script) {
        script.echo "#=================================================="
        script.sh "pwd"
        script.echo "#=================================================="
    }

    static def ps(def script) {
        script.echo "#=================================================="
        script.sh "ps -ef"
        script.echo "#=================================================="
    }

    static def ls(def script) {
        script.echo "#=================================================="
        script.sh "ls -al"
        script.echo "#=================================================="
    }
}
