package notifiers;

public class ConsoleNotifier implements Notifiable {
    @Override
    public void notifyChange(String message) {
        System.out.println(message);
    }
}
