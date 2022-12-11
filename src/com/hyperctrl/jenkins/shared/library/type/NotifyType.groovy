package com.hyperctrl.jenkins.shared.library.type

enum NotifyType {

    EMAIL("Email", "EMAIL"),

    TEAMS("Teams", "TEAMS"),

    SLACK("Slack", "SLACK"),

    DINGTALK("DingTalk", "DINGTALK")

    String description

    String value

    NotifyType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        NotifyType.values().each { NotifyType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        NotifyType.values().each { NotifyType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}