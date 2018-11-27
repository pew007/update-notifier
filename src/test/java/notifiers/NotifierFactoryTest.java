package notifiers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifierFactoryTest {

    @Test
    void createConsoleNotifier() {
        String[] args = new String[]{};
        Notifiable notifier = NotifierFactory.create("console", args);

        Assertions.assertTrue(notifier instanceof ConsoleNotifier);
    }

    @Test
    void createEmailNotifer() {
        String[] args = new String[]{"test@gmail.com"};
        Notifiable notifier = NotifierFactory.create("mail", args);

        Assertions.assertTrue(notifier instanceof EmailNotifier);
    }

    @Test
    void createSmsNotifier() {
        String[] args = new String[]{"6262285115", "tmobile"};
        Notifiable notifier = NotifierFactory.create("sms", args);
    }
}