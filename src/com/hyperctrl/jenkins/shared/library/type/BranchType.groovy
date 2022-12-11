package com.hyperctrl.jenkins.shared.library.type

enum BranchType {

    MASTER("master", "MASTER"),
    DEV("dev", "DEV"),
    TEST("test", "TEST"),
    DEMO("demo", "DEMO"),
    PREP("prep", "PREP")

    String description
    String value

    BranchType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        BranchType.values().each { BranchType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        BranchType.values().each { BranchType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}