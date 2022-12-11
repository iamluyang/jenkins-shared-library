package com.hyperctrl.jenkins.shared.library.pipline.impl

import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IJenkinsPipeline
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.stage.plugin.parameters.ParametersPipelineStage
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.LogUtils
import com.hyperctrl.jenkins.shared.library.utils.ScannerUtils
import com.hyperctrl.jenkins.shared.library.utils.StringUtils
import groovy.util.logging.Slf4j

@Slf4j
class DefaultJenkinsPipeline implements IJenkinsPipeline {

    private static String packageName = "com.hyperctrl.jenkins"

    private Map<StageType, Map<String, IPipelineStage>> pipelineStages = new HashMap()

    /**
     *
     */
    DefaultJenkinsPipeline() {

    }

    @Override
    void init() {

    }

    @Override
    void scanPipeStages() {
        Set<Class<IPipelineStage>> pipelineStageClasses = ScannerUtils.scanByClass(packageName, IPipelineStage.class)
        for (Class<IPipelineStage> pipelineStageClass : pipelineStageClasses) {
            this.addPipeStage(pipelineStageClass.getDeclaredConstructor().newInstance())
        }
    }

    @Override
    void addPipeStage(IPipelineStage pipelineStage) {
        PipelineStage annotation = pipelineStage.getClass().getAnnotation(PipelineStage.class)

        Map<String, IPipelineStage> pipelineStageOf = pipelineStages.get(annotation.stage())
        if (pipelineStageOf == null) {
            pipelineStageOf = new HashMap<>()
            pipelineStages.put(annotation.stage(), pipelineStageOf)
        }
        pipelineStageOf.put(pipelineStage.getType(), pipelineStage)
    }

    @Override
    void removePipeStage(IPipelineStage pipelineStage) {
        PipelineStage annotation = pipelineStage.getClass().getAnnotation(PipelineStage.class)

        Map<String, IPipelineStage> pipelineStageOf = pipelineStages.get(annotation.stage())
        if (pipelineStageOf != null) {
            pipelineStageOf.remove(pipelineStage.getType())
        }
    }

    @Override
    def execute(Object script, StageType stageType) {
        return this.execute(script, stageType, StringUtils.NONE)
    }

    @Override
    def execute(Object script, IPipelineStage pipelineStage) {
        if (pipelineStage != null) {
            PipelineStage annotation = pipelineStage.getClass().getAnnotation(PipelineStage.class)
            this.execute(script, annotation.stage(), pipelineStage.getType())
        }
        return null
    }

    @Override
    def execute(Object script, StageType stageType, String type) {
        IPipelineStage pipelineStage = this.getPipelineStage(stageType, type)
        if (pipelineStage != null) {
            PipelineStage annotation = pipelineStage.getClass().getAnnotation(PipelineStage.class)
            if (pipelineStage.can(script)) {
                LogUtils.log(script, "Execute ${annotation.name()}")
                return pipelineStage.execute(script)
            } else {
                LogUtils.log(script, "Skip Execute ${annotation.name()}")
            }
        }
        return null
    }

    @Override
    IPipelineStage getPipelineStage(StageType stageType, String type) {
        Map<String, IPipelineStage> pipelineStageOf = pipelineStages.get(stageType)
        if (pipelineStageOf != null) {
            return pipelineStageOf.get(type)
        }
        return null
    }

    static void main(String[] args) {
        new DefaultJenkinsPipeline().addPipeStage(new ParametersPipelineStage())
    }
}
