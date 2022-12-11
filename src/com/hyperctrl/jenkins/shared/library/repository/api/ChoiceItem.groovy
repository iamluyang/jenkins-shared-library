package com.hyperctrl.jenkins.shared.library.repository.api

class ChoiceItem {
    private String description
    private String value

    ChoiceItem(String description, String value) {
        this.description = description
        this.value = value
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }
}
