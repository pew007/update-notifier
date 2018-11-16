package notifiers;

public class NotifierFactory {

    public static Notifiable create(String notifyMethod, String[] args) {
        switch (notifyMethod) {
            case "console":
                return new ConsoleNotifier();
            case "mail":
                String recipient = args[0];
                EmailNotifier notifier = new EmailNotifier();
                notifier.addRecipient(recipient);

                return notifier;
            case "sms":
                String phone = args[0];
                String carrier = args[1];
                return new SmsNotifier();
            default:
                return null;
        }
    }
}
