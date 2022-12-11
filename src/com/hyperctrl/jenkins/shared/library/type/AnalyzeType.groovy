package com.hyperctrl.jenkins.shared.library.type

enum AnalyzeType {

    SONARQUBE("Sonarqube", "SONARQUBE"),

    FORTIFY("Fortify", "FORTIFY")

    String description

    String value

    AnalyzeType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        AnalyzeType.values().each { AnalyzeType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        AnalyzeType.values().each { AnalyzeType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}