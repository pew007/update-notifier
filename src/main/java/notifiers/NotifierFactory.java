package notifiers;

public class NotifierFactory {

    public static Notifiable create(String notifyMethod, String[] args) {
        switch (notifyMethod) {
            case "console":
                return new ConsoleNotifier();
            case "mail":
                String recipient = args[0];
                EmailNotifier emailNotifier = new EmailNotifier();
                emailNotifier.addRecipient(recipient);
                return emailNotifier;
            case "sms":
                String number = args[0];
                String carrier = args[1];
                SmsNotifier smsNotifier = new SmsNotifier();
                smsNotifier.addRecipient(number, carrier);
                return smsNotifier;
            default:
                return null;
        }
    }
}
