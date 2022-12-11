package com.hyperctrl.jenkins.shared.library.stage.plugin.notify

import com.hyperctrl.jenkins.shared.library.bean.yaml.YamlFile
import com.hyperctrl.jenkins.shared.library.bean.yaml.stage.Dingtalk
import com.hyperctrl.jenkins.shared.library.bean.yaml.stage.Email
import com.hyperctrl.jenkins.shared.library.bean.yaml.stage.Slack
import com.hyperctrl.jenkins.shared.library.bean.yaml.stage.Teams
import com.hyperctrl.jenkins.shared.library.pipline.anno.PipelineStage
import com.hyperctrl.jenkins.shared.library.pipline.api.IPipelineStage
import com.hyperctrl.jenkins.shared.library.service.impl.NotifyService
import com.hyperctrl.jenkins.shared.library.type.NotifyType
import com.hyperctrl.jenkins.shared.library.type.StageType
import com.hyperctrl.jenkins.shared.library.utils.EmailUtils
import com.hyperctrl.jenkins.shared.library.utils.StringUtils
import com.hyperctrl.jenkins.shared.library.utils.YamlUtils

@PipelineStage(name = "Notify Pipeline Stage", stage = StageType.NOTIFY)
class DefaultNotifyPipelineStage implements IPipelineStage {

    private NotifyService notifyService

    DefaultNotifyPipelineStage() {
        notifyService = new NotifyService()
    }

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
        YamlFile yamlFile = YamlUtils.load(script)
        String subject = EmailUtils.getDefaultEmailSubject(script)
        String content = EmailUtils.getDefaultEmailContent(script)

        String notifyType = script.params.NotifyType
        String[] types = notifyType.split(",")
        for (String type : types) {
            if(type.equalsIgnoreCase(NotifyType.EMAIL.name())) {
                Email notify = yamlFile.jenkins.global.stages.notify.email
                notifyService.sendEmailMessage(script, subject, content, notify.from, notify.to, notify.smtpHost, notify.smtpPort)
            }
            if(type.equalsIgnoreCase(NotifyType.SLACK.name())) {
                Slack notify = yamlFile.jenkins.global.stages.notify.slack
                notifyService.sendSlackMessage(notify.webhook, subject, content)
            }
            if(type.equalsIgnoreCase(NotifyType.DINGTALK.name())) {
                Dingtalk notify = yamlFile.jenkins.global.stages.notify.dingtalk
                notifyService.sendDingTalkMessage(notify.webhook, subject, content)
            }
            if(type.equalsIgnoreCase(NotifyType.TEAMS.name())) {
                Teams notify = yamlFile.jenkins.global.stages.notify.teams
                notifyService.sendTeamsMessage(notify.webhook, subject, content)
            }
        }
    }
}