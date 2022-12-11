package com.hyperctrl.jenkins.shared.library.utils

class LogUtils {

    private LogUtils() {
    }

    static String log(def script, String message) {
        script.echo "#=================================================="
        script.echo "# " + message
        script.echo "#=================================================="
    }
}
