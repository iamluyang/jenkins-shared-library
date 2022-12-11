package com.hyperctrl.jenkins.shared.library.utils


import javax.mail.Message
import javax.mail.Multipart
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class EmailUtils {

    static void sendEmail(def script, String username, String password, String subject, String content, String from, String[] to, String smtpHost, int smtpPort = 25) {
        java.net.PasswordAuthentication
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true)
        prop.put("mail.smtp.host", smtpHost)
        prop.put("mail.smtp.port", smtpPort)

        MyAuthenticator authenticator = new MyAuthenticator(username, password);
        Session session = Session.getInstance(prop, authenticator)

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        for (String emailAddress : to) {
            LogUtils.log(script, "Send Email -> \n" +
                    "username:${username};\n" +
                    "password:******;\n" +
                    "subject:${subject};\n" +
                    "content:${content};\n" +
                    "from:${from};\n" +
                    "to:${to};\n" +
                    "smtpHost:${smtpHost};\n" +
                    "smtpPort:${smtpPort}")
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
        }

        message.setSubject(subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message)
    }

    static String getDefaultEmailSubject(def script) {
        String subject = "Jenkins Job - ${JenkinsUtils.getBuildTag(script)}"
        return subject
    }

    static String getDefaultEmailContent(def script) {
        String txt = "" +
                "# Jenkins Job - ${JenkinsUtils.getJobName(script)}\n" +
                "- **Node  Name**:${JenkinsUtils.getNodeName(script)}\n" +
                "- **User  Id**:${JenkinsUtils.getUserIdOfgetBuildCauses(script)}\n" +
                "- **Build Id**:${JenkinsUtils.getBuildId(script)}\n" +
                "- **Build Tag**:${JenkinsUtils.getBuildTag(script)}\n" +
                "- **Build Number**:${JenkinsUtils.getBuildNumber(script)}\n" +
                "- **Executor Number**:${JenkinsUtils.getExecutorNumber(script)}\n" +
                "- **Description**:${JenkinsUtils.getShortDescriptionOfgetBuildCauses(script)}\n" +
                "- **Duration **:${JenkinsUtils.getDurationString(script)}\n" +
                "- **Result**:${JsonUtils.toJson(JenkinsUtils.getCurrentResult(script))}\n" +
                "- **[Goto](${JenkinsUtils.getBuildUrl(script)})**"
        return txt
    }

    /*static void main(String[] args) {
        EmailUtils.sendEmail("msluyang@outlook.com", "Summerlu19821014", "yyy", "yy", "msluyang@outlook.com", new String[] {"309849305@qq.com"}, "smtp.office365.com", 587)
    }*/
}
