package com.hyperctrl.jenkins.shared.library.utils

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider

class GitUtils {

    private GitUtils() {
    }
    
    static Git getGit(String username, String password) {
        Git git = Git.cloneRepository().setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password)).call()
        return git
    }
}
