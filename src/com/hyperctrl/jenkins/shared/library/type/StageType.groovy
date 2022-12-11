package com.hyperctrl.jenkins.shared.library.type

final enum StageType {

    /**
     * NONE
     */
    NONE,

    /**
     * Parameters
     */
    PARAMETERS,

    /**
     * Welcome
     */
    WELCOME,

    /**
     * Version
     */
    VERSION,

    /**
     * Build
     */
    BUILD,

    /**
     * Unit Test
     */
    UNIT_TESTING,

    /**
     * Integration Test
     */
    INTEGRATION_TESTING,

    /**
     * Health Check Test
     */
    HEALTHCHECK_TESTING,

    /**
     * Code Analysis
     */
    ANALYZE,

    /**
     * Build Package Artifact
     */
    ARTIFACT_BUILD,

    /**
     * Deploy Package Artifact
     */
    ARTIFACT_DEPLOY,

    /**
     * Build Docker Image
     */
    DOCKER_BUILD,

    /**
     * Push Docker Image
     */
    DOCKER_PUSH,

    /**
     * Deployment
     */
    DEPLOY,

    /**
     * Report
     */
    REPORT,

    /**
     * notification
     */
    NOTIFY,

    /**
     * domain
     */
    DOMAIN,

    /**
     * monitor
     */
    MONITOR,

    static String[] getList() {
        StageType.values().collect { it.name() }
    }
}
