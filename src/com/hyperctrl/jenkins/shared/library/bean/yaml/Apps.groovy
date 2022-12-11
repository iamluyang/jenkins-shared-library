package com.hyperctrl.jenkins.shared.library.bean.yaml

class Apps {
    App app = new App()
}

class App {
    boolean enable = true
    String root
    String name
    Pipeline pipeline
}