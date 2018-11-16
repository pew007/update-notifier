package clients;

import javax.mail.MessagingException;
import java.util.List;

public class TextMessageClient {

    private EmailClient emailClient;

    public TextMessageClient() {
        this.emailClient = this.emailClient();
    }

    EmailClient emailClient() {
        return new EmailClient();
    }

    public void send(String subject, String message, List<String> recipients) throws MessagingException {
        emailClient.send(subject, message, recipients);
    }
}
