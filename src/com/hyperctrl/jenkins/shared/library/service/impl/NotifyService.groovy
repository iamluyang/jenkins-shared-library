package com.hyperctrl.jenkins.shared.library.service.impl


import com.hyperctrl.jenkins.shared.library.bean.notify.DingTalkMarkdownMessage
import com.hyperctrl.jenkins.shared.library.bean.notify.SlackMessage
import com.hyperctrl.jenkins.shared.library.service.api.INotificationService
import com.hyperctrl.jenkins.shared.library.utils.CredentialsUtils
import com.hyperctrl.jenkins.shared.library.utils.EmailUtils
import com.hyperctrl.jenkins.shared.library.utils.HttpUtils
import com.hyperctrl.jenkins.shared.library.utils.JenkinsCredential

import java.net.http.HttpResponse

class NotifyService implements INotificationService {

    @Override
    HttpResponse<String> sendSlackMessage(String webhook, String subject, String content) {
        SlackMessage message = new SlackMessage("${subject}->${content}")
        return HttpUtils.post(webhook, message)
    }

    @Override
    HttpResponse<String> sendDingTalkMessage(String webhook, String subject, String content) {
        DingTalkMarkdownMessage message = new DingTalkMarkdownMessage()
        message.getMarkdown().setTitle(subject)
        message.getMarkdown().setText(content)

        return HttpUtils.post(webhook, message)
    }

    @Override
    HttpResponse<String> sendTeamsMessage(String webhook, String subject, String content) {
        return HttpUtils.post(webhook, message)
    }

    @Override
    void sendEmailMessage(def script, String subject, String content, String from, String[] to, String smtpHost, int smtpPort) {
        String smtpCredentialsId = script.params.SmtpCredentialsId
        JenkinsCredential credential = CredentialsUtils.getCredentials(script, smtpCredentialsId)
        EmailUtils.sendEmail(script, credential.username, credential.password, subject, content, from, to, smtpHost, smtpPort)
    }
}
