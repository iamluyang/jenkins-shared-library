package com.hyperctrl.jenkins.shared.library.utils

class JenkinsCredential {

    JenkinsCredential(String username, String password) {
        this.username = username
        this.password = password
    }

    String username
    String password
}
