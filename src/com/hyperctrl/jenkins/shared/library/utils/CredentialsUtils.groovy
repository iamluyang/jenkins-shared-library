package com.hyperctrl.jenkins.shared.library.utils

import com.cloudbees.plugins.credentials.Credentials
import com.cloudbees.plugins.credentials.CredentialsProvider

class CredentialsUtils {

    private CredentialsUtils() {
    }

    static JenkinsCredential getCredentials(def script, String credentialId) {
        List<Credentials> jenkinsCredentials = CredentialsProvider.lookupCredentials(Credentials.class)

        for (credentials in jenkinsCredentials) {
            if(credentials.id.equals(credentialId)){
                return new JenkinsCredential(credentials.username, credentials.password.value)
            }
        }
        return null
    }
}
