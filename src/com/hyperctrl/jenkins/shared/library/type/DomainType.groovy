package com.hyperctrl.jenkins.shared.library.type

enum DomainType {

    /**
     * Prometheus
     */
    ALIYUN("Aliyun", "ALIYUN")

    String description

    String value

    DomainType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        DomainType.values().each { DomainType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        DomainType.values().each { DomainType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}
