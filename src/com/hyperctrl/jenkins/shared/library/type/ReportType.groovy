package com.hyperctrl.jenkins.shared.library.type

enum ReportType {

    /**
     * UNIT_TEST
     */
    UNIT_TEST("Unit Test Report", "UNIT_TEST")

    String description

    String value

    ReportType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        ReportType.values().each { ReportType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        ReportType.values().each { ReportType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}
