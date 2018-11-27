package notifiers;

import clients.MockTextMessageClient;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

import static org.mockito.Mockito.*;

class SmsNotifierTest {

    @Test
    void notifyChange() throws MessagingException {
        MockTextMessageClient mockClient = new MockTextMessageClient();
        SmsNotifier notifier = new SmsNotifier(mockClient);

        notifier.notifyChange("Test", "Test");

        verify(mockClient.getEmailClient().getTransport(), times(1)).sendMessage(any(), any());
    }
}
