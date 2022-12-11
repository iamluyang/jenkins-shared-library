package com.hyperctrl.jenkins.shared.library.type

enum CIType {

    /**
     * Publish Java Package with Maven.
     */
    JAVA_WITH_MAVEN("Java With Maven", "JAVA_WITH_MAVEN"),

    /**
     * Publish Java Package with Gradle.
     */
    JAVA_WITH_GRADLE("Java With Gradle", "JAVA_WITH_GRADLE"),

    /**
     * Build and test a Java project with Apache Ant.
     */
    JAVA_WITH_ANT("Java With Ant", "JAVA_WITH_ANT"),

    /**
     * Build and test a Node.js project with npm.
     */
    NODEJS("NodeJs", "NODEJS"),

    /**
     * Create and test a Python package on multiple Python versions using Anaconda for package management.
     */
    PYTHON("Python", "PYTHON"),

    /**
     * Build and test a .NET or ASP.NET Core project.
     */
    DOTNET(".Net", "DOTNET"),

    /**
     * Build a database
     */
    DATABASE("Database", "DATABASE")

    String description

    String value

    CIType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        CIType.values().each { CIType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        CIType.values().each { CIType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}