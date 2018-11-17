package notifiers;

import clients.TextMessageClient;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SmsNotifier implements Notifiable {

    private List<String> recipients;
    private TextMessageClient messageClient;

    public static final Map<String, String> carrierDomainMap = Stream.of(
            new AbstractMap.SimpleEntry<>("alltel", "mms.alltelwireless.com"),
            new AbstractMap.SimpleEntry<>("att", "mms.att.net"),
            new AbstractMap.SimpleEntry<>("boostmobile", "myboostmobile.com"),
            new AbstractMap.SimpleEntry<>("cricket", "mms.cricketwireless.net"),
            new AbstractMap.SimpleEntry<>("fi", "msg.fi.google.com"),
            new AbstractMap.SimpleEntry<>("sprint", "pm.sprint.com"),
            new AbstractMap.SimpleEntry<>("tmobile", "tmomail.net"),
            new AbstractMap.SimpleEntry<>("uscellular", "mms.uscc.net"),
            new AbstractMap.SimpleEntry<>("verizon", "vzwpix.com")
    ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    SmsNotifier() {
        this.recipients = new ArrayList<>();
        this.messageClient = this.messageClient();
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
