package com.hyperctrl.jenkins.shared.library.stage.plugin.domain

import com.aliyuncs.domain.model.v20180129.QueryDomainListResponse
import com.cloudbees.plugins.credentials.domains.Domain
import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.service.impl.AliyunService
import com.hyperctrl.jenkins.shared.library.type.DomainType
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.JsonUtils
import com.hyperctrl.jenkins.shared.library.utils.LogUtils

@PipelineStage(name = "Aliyun Domain Pipeline Stage", stage = StageType.DOMAIN)
class AliyunDomainPipelineStage implements IPipelineStage {

    @Override
    String getType() {
        return DomainType.ALIYUN.name()
    }

    @Override
    boolean can(def script) {
        return script.params.DomainNameEnable
    }

    @Override
    def execute(def script) {
        AliyunService aliyunService = new AliyunService(script)
        aliyunService.login()
        QueryDomainListResponse response = aliyunService.getScrollDomainList()
        for (Domain domain : response.data) {
            LogUtils.log(script, JsonUtils.toJson(domain))
        }
    }
}