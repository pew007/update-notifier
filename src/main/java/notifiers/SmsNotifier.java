package notifiers;

import clients.TextMessageClient;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SmsNotifier implements Notifiable {

    private List<String> recipients = new ArrayList<>();
    private TextMessageClient messageClient;

    private static final Map<String, String> carrierDomainMap = Stream.of(
            new SimpleEntry<>("alltel", "mms.alltelwireless.com"),
            new SimpleEntry<>("att", "mms.att.net"),
            new SimpleEntry<>("boostmobile", "myboostmobile.com"),
            new SimpleEntry<>("cricket", "mms.cricketwireless.net"),
            new SimpleEntry<>("fi", "msg.fi.google.com"),
            new SimpleEntry<>("sprint", "pm.sprint.com"),
            new SimpleEntry<>("tmobile", "tmomail.net"),
            new SimpleEntry<>("uscellular", "mms.uscc.net"),
            new SimpleEntry<>("verizon", "vzwpix.com")
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    SmsNotifier() {
        this.messageClient = this.messageClient();
    }

    SmsNotifier(TextMessageClient client) {
        this.messageClient = client;
    }

    void addRecipient(String number, String carrier) {
        String carrierDomain = carrierDomainMap.get(carrier);
        String recipient = String.format("%s@%s", number, carrierDomain);
        recipients.add(recipient);
    }

    TextMessageClient messageClient() {
        return new TextMessageClient();
    }

    @Override
    public void notifyChange(String subject, String message) {
        try {
            messageClient.send(subject, message, recipients);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
