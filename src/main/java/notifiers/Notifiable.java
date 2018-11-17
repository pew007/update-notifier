package notifiers;

public interface Notifiable {
    void notifyChange(String subject, String message);
}
