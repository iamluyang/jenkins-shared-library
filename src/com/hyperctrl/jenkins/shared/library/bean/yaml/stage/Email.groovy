package com.hyperctrl.jenkins.shared.library.bean.yaml.stage

class Email {
    String smtpHost
    int smtpPort
    String from
    String[] to
    boolean starttls = true
}