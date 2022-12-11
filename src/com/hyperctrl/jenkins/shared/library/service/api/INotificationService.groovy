package com.hyperctrl.jenkins.shared.library.service.api


import java.net.http.HttpResponse

interface INotificationService {

    HttpResponse<String> sendSlackMessage(String webhook, String subject, String content);

    HttpResponse<String> sendDingTalkMessage(String webhook, String subject, String content);

    HttpResponse<String> sendTeamsMessage(String webhook, String subject, String content);

    void sendEmailMessage(def script, String subject, String content, String from, String[] to, String smtpHost, int smtpPort)
}