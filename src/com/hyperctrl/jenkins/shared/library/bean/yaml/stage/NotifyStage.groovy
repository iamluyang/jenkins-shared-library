package com.hyperctrl.jenkins.shared.library.bean.yaml.stage

class NotifyStage {
    Slack slack = new Slack()
    Teams teams = new Teams()
    Dingtalk dingtalk = new Dingtalk()
    Email email = new Email()
}
