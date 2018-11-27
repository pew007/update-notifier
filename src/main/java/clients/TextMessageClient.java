package clients;

import javax.mail.MessagingException;
import java.util.List;

public class TextMessageClient {

    EmailClient emailClient;

    public TextMessageClient() {
        this.emailClient = this.emailClient();
    }

    EmailClient emailClient() {
        return new EmailClient();
    }

    public EmailClient getEmailClient() {
        return this.emailClient;
    }

    public void send(String subject, String message, List<String> recipients) throws MessagingException {
        emailClient.send(subject, message, recipients);
    }
}
