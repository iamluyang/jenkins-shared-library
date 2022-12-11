package com.hyperctrl.jenkins.shared.library.stage.plugin.welcome

@Grab('org.yaml:snakeyaml:1.17')
@Grab('javax.mail:javax.mail-api:1.6.2')
@Grab('com.sun.mail:javax.mail:1.6.2')
@Grab('com.google.code.gson:gson:2.10')
@Grab('com.aliyun:aliyun-java-sdk-core:4.6.1')
@Grab('com.aliyun:aliyun-java-sdk-alidns:3.0.1')
@Grab('com.aliyun:aliyun-java-sdk-domain:3.14.11')
@Grab('org.eclipse.jgit:org.eclipse.jgit:6.4.0.202211300538-r')
import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.JenkinsUtils
import com.hyperctrl.jenkins.shared.library.utils.StringUtils

@PipelineStage(name = "Welcome Pipeline Stage", stage = StageType.WELCOME)
class WelcomePipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return StringUtils.NONE
    }

    @Override
    boolean can(def script) {
        return true
    }

    @Override
    def execute(def script) {
        JenkinsUtils.envs(script)
        JenkinsUtils.pwd(script)
        JenkinsUtils.ls(script)
    }
}
