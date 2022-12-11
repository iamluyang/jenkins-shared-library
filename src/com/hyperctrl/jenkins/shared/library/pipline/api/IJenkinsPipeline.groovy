package com.hyperctrl.jenkins.shared.library.pipline.api


import com.hyperctrl.jenkins.shared.library.type.StageType

interface IJenkinsPipeline {

    /**
     * init
     */
    void init();

    /**
     *
     */
    void scanPipeStages();

    /**
     *
     * @param pipelineStage
     */
    void addPipeStage(IPipelineStage pipelineStage);

    /**
     *
     * @param pipelineStage
     */
    void removePipeStage(IPipelineStage pipelineStage);

    /**
     *
     * @param script
     * @param stageType
     * @return
     */
    def execute(Object script, StageType stageType)

    /**
     *
     * @param script
     * @param pipelineStage
     * @return
     */
    def execute(Object script, IPipelineStage pipelineStage);

    /**
     *
     * @param script
     * @param stageType
     * @param catalog
     * @return
     */
    def execute(def script, StageType stageType, String catalog);

    /***
     *
     * @param stageType
     * @param catalog
     * @return
     */
    IPipelineStage getPipelineStage(StageType stageType, String catalog);

}