package clients;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailClient {

    private static final String contentType = "text/plain";
    private static final String protocol = "smtp";
    private static final String host = "smtp.gmail.com";

    Transport transport;
    private Session session;
    private String user;
    private String pass;

    public EmailClient() {
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        session = Session.getDefaultInstance(mailServerProperties, null);
        transport = this.transport();

        user = System.getenv("JAVA_MAIL_USER");
        pass = System.getenv("JAVA_MAIL_PASS");
    }

    Transport transport() {
        try {
            return session.getTransport(protocol);
        } catch (Exception exception) {
            return null;
        }
    }

    public void send(String subject, String emailBody, List<String> recipients) throws MessagingException {
        Message message = this.generateMessage(subject, emailBody, recipients);
        transport.connect(host, user, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    MimeMessage generateMessage(String subject, String emailBody, List<String> recipients) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        for (String recipient : recipients) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        message.setSubject(subject);
        message.setContent(emailBody, contentType);
        message.setHeader("Content-Type", contentType);

        return message;
    }
}
