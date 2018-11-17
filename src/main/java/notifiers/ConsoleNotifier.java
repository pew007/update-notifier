package notifiers;

public class ConsoleNotifier implements Notifiable {
    @Override
    public void notifyChange(String subject, String message) {
        System.out.println(message);
    }
}
