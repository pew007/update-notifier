package notifiers;

import clients.EmailClient;

import java.util.ArrayList;
import java.util.List;

public class EmailNotifier implements Notifiable {

    private List<String> recipients;
    private EmailClient emailClient;

    EmailNotifier() {
        this.recipients = new ArrayList<>();
        this.emailClient = this.emailClient();
    }

    EmailClient emailClient() {
        return new EmailClient();
    }

    void addRecipient(String recipient) {
        this.recipients.add(recipient);
    }

    @Override
    public void notifyChange(String subject, String message) {
        try {
            emailClient.send(subject, message, recipients);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
