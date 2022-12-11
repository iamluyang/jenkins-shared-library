package com.hyperctrl.jenkins.shared.library.type

enum VCType {

    GIT("Git", "GIT"),

    SVN("Svn", "SVN"),

    CVS("Cvs", "CVS")

    String description
    String value

    VCType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        VCType.values().each { VCType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        VCType.values().each { VCType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}