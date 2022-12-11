package com.hyperctrl.jenkins.shared.library.bean.notify

class At{
    boolean isAtAll = true
}

class Markdown{
    String title;
    String text;
}

class DingTalkMarkdownMessage{
    String msgtype = "markdown"
    Markdown markdown = new Markdown()
    At at = new At()
}