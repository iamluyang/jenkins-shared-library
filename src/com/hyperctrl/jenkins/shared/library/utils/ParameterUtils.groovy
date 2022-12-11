package com.hyperctrl.jenkins.shared.library.utils

import com.hyperctrl.jenkins.shared.library.repository.api.ChoiceItem

class ParameterUtils {

    private ParameterUtils() {
    }

    static String separator(Class clazz, String title) {
        def separator = [
            name: clazz.getSimpleName(),
            sectionHeader: title,
            separatorStyle: "border-width: 0",
            sectionHeaderStyle: """
				background-color: #7ea6d3;
				text-align: center;
				padding: 10px;
				color: #343434;
				font-size: 22px;
				font-weight: normal;
				text-transform: uppercase;
				font-family: 'Orienta', sans-serif;
				letter-spacing: 1px;
				font-style: italic;
			"""
        ]
        return separator
    }

    static def stringParam(def script, String name, String description, String defaultValue = "") {
        return script.string(
                defaultValue: defaultValue,
                description: description,
                name: name)
    }

    static def booleanParam(def script, String name, String description, boolean value = false) {
        return script.booleanParam(
                defaultValue: value,
                description: description,
                name: name)
    }

    static def choiceParam(def script, String name, String description, Collection<String> values) {
        return script.choice(
                choices: values,
                description: description,
                name: name)
    }

    static def usernamePasswordCredentialsParam(def script, String name, String description, String defaultValue = null) {
        return  script.credentials(
                credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl',
                defaultValue: defaultValue,
                description: description,
                name: name,
                required: false)
    }

    static def basicSSHUserPrivateKeyParam(def script, String name, String description, String defaultValue = null) {
        return script.credentials(
                credentialType: 'com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey',
                defaultValue: defaultValue,
                description: description,
                name: name,
                required: false)
    }


    static def choiceSingleSelectParam(def script, String name, String description, String descriptions, String values, String defaultValue = null, int visibleItemCount = 5) {
        return extendedChoiceBasicParam(script, "PT_SINGLE_SELECT", name, description, descriptions, values, defaultValue, visibleItemCount)
    }

    static def choiceMultiSelectParam(def script, String name, String description, String descriptions, String values, String defaultValue = null, int visibleItemCount = 5) {
        return extendedChoiceBasicParam(script, "PT_MULTI_SELECT", name, description, descriptions, values, defaultValue, visibleItemCount)
    }

    static def choiceCheckboxParam(def script, String name, String description, String descriptions, String values, String defaultValue = null, int visibleItemCount = 5) {
        return extendedChoiceBasicParam(script, "PT_CHECKBOX", name, description, descriptions, values, defaultValue, visibleItemCount)
    }

    static def choiceRadioParam(def script, String name, String description, String descriptions, String values, String defaultValue = null, int visibleItemCount = 5) {
        return extendedChoiceBasicParam(script, "PT_RADIO", name, description, descriptions, values, defaultValue, visibleItemCount)
    }

    static def choiceTextBoxParam(def script, String name, String description, String descriptions, String values, String defaultValue = null, int visibleItemCount = 5) {
        return extendedChoiceBasicParam(script, "PT_TEXTBOX", name, description, descriptions, values, defaultValue, visibleItemCount)
    }

    private static def extendedChoiceBasicParam(def script, String type, String name, String description, String descriptions, String values, String defaultValue = null, int visibleItemCount = 5) {
        return script.extendedChoice(
                defaultValue: defaultValue,
                description: description,
                descriptionPropertyValue: descriptions,
                multiSelectDelimiter: ',',
                name: name,
                quoteValue: false,
                saveJSONParameterToFile: false,
                type: type,
                value: values,
                visibleItemCount: 5)
    }

    static String getChoices(String[] items) {
        StringBuilder sb = new StringBuilder()
        for (String item : items) {
            sb.append(item).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getDescriptions(String[] items) {
        StringBuilder sb = new StringBuilder()
        for (String item : items) {
            sb.append(item.split(":")[0]).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues(String[] items) {
        StringBuilder sb = new StringBuilder()
        for (String item : items) {
            sb.append(item.split(":")[1]).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getDescriptions(Collection<ChoiceItem> items) {
        StringBuilder sb = new StringBuilder()
        for (ChoiceItem item : items) {
            sb.append(item.getDescription()).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues(Collection<ChoiceItem> items) {
        StringBuilder sb = new StringBuilder()
        for (ChoiceItem item : items) {
            sb.append(item.getValue()).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}
