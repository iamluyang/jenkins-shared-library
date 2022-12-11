package com.hyperctrl.jenkins.shared.library.utils

class StringUtils {

    private StringUtils() {
    }

    static String NONE = "NONE"

    static boolean trimEndForwardSlash(String value) {
        if (value != null && value.endsWith("/")) {
            return value.substring(0, value.length() - 1)
        }
        return value
    }
}
