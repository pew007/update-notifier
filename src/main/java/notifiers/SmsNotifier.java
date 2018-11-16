package notifiers;

public class SmsNotifier implements Notifiable {

    private String phone;
    private String carrier;

    public SmsNotifier() {
    }

    @Override
    public void notifyChange(String message) {

    }
}
