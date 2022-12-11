package com.hyperctrl.jenkins.shared.library.utils

class MyAuthenticator extends javax.mail.Authenticator {
    String userName = null;
    String password = null;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(userName, password);
    }
}