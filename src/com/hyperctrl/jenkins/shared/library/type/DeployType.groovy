package com.hyperctrl.jenkins.shared.library.type

enum DeployType {

    /**
     * Apache Tomcat is a free and open-source implementation of the Jakarta Servlet.
     */
    TOMCAT("Apache Tomcat", "TOMCAT"),

    /**
     * WebSphere Application Server is a software product that performs the role of a web application server.
     */
    WEBSPHERE("IBM WebSphere", "WEBSPHERE"),

    /**
     * Deploy an application to an Azure Kubernetes Service cluster.
     */
    AZURE_AKS("Azure AKS", "AZURE_AKS"),

    /**
     * Build a container and deploy it to an Azure Web App.
     */
    AZURE_WebApp("Azure WebApp", "AZURE_WebApp"),

    /**
     * Deploy to Amazon EKS
     * Amazon Elastic Kubernetes Service (EKS)
     */
    AWS_EKS("AWS EKS", "AWS_EKS"),

    /**
     * Deploy a container to an Amazon ECS service powered by AWS Fargate or Amazon EC2.
     */
    AWS_ECS("AWS ECS", "AWS_ECS"),

    /**
     * Build and Deploy to GKE
     * Build a docker container, publish it to Google Container Registry, and deploy to GKE.
     */
    GCP_GKE("GCP GKE", "GCP_GKE"),

    /**
     * Deploy to Alibaba Cloud ACK
     * Deploy a container to Alibaba Cloud Container Service for Kubernetes (ACK).
     */
    ALI_ACK("Aliyun ACK", "ALI_ACK"),

    /**
     * Deploy to IBM Cloud Kubernetes Service
     * Build a docker container, publish it to IBM Cloud Container Registry, and deploy to IBM Cloud Kubernetes Service.
     */
    IBM_CKS("Tencent TKE", "IBM_CKS"),

    /**
     * Tencent Kubernetes Engine
     * This workflow will build a docker container, publish and deploy it to Tencent Kubernetes Engine (TKE).
     */
    TENCENT_TKE("Tencent TKE", "TENCENT_TKE"),

    /**
     * OpenShift
     * Build a Docker-based project and deploy it to OpenShift.
     */
    REDHAT_OPENSHIFT("OpenShift", "REDHAT_OPENSHIFT")

    String description

    String value

    DeployType(String description, String value) {
        this.description = description
        this.value = value
    }

    static String getDescriptions() {
        StringBuilder sb = new StringBuilder()
        DeployType.values().each { DeployType item ->
            sb.append(item.description).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }

    static String getValues() {
        StringBuilder sb = new StringBuilder()
        DeployType.values().each { DeployType item ->
            sb.append(item.value).append(",")
        }
        return sb.substring(0, sb.length()-1)
    }
}