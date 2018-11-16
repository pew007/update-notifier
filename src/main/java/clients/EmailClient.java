package clients;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailClient {

    private static final String contentType = "text/plain";
    private static final String protocol = "smtp";
    private static final String host = "smtp.gmail.com";

    private Properties mailServerProperties;
    private String user;
    private String pass;

    public EmailClient() {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        user = System.getenv("JAVA_MAIL_USER");
        pass = System.getenv("JAVA_MAIL_PASS");
    }

    public void send(String subject, String emailBody, List<String> recipients) throws MessagingException {
        Message message = this.generateMessage(subject, emailBody, recipients);
        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        Transport transport = getMailSession.getTransport(protocol);
        transport.connect(host, user, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    MimeMessage generateMessage(String subject, String emailBody, List<String> recipients) throws MessagingException {
        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage message = new MimeMessage(getMailSession);
        for (String recipient : recipients) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        message.setSubject(subject);
        message.setContent(emailBody, contentType);
        message.setHeader("Content-Type", contentType);

        return message;
    }
}
