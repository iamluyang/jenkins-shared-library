package com.hyperctrl.jenkins.shared.library.type

enum MonitorType {

    /**
     * Prometheus
     */
    Prometheus("Prometheus", "Prometheus")

    String description

    String value

    MonitorType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        MonitorType.values().each { MonitorType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        MonitorType.values().each { MonitorType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}
