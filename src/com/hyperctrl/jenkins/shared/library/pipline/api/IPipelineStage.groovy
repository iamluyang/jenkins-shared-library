package com.hyperctrl.jenkins.shared.library.pipline.api

interface IPipelineStage {

    String getType();

    /**
     * can execute
     * @param script
     * @return
     */
    boolean can(def script);

    /**
     * execute
     * @param script
     */
    def execute(def script);
}