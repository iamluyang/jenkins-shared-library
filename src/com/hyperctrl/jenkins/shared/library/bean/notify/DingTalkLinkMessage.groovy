package com.hyperctrl.jenkins.shared.library.bean.notify

class DingTalkLinkMessage {

    String msgtype = "link"

    Link link = new Link()

    static class Link {
        String text
        String title
        String picUrl
        String messageUrl
    }
}
