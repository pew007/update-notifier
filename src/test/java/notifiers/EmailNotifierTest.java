package notifiers;

import clients.MockEmailClient;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

import static org.mockito.Mockito.*;

class EmailNotifierTest {

    @Test
    void notifyChange() throws MessagingException {
        MockEmailClient clientMock = new MockEmailClient();
        EmailNotifier notifier = new EmailNotifier(clientMock);

        notifier.notifyChange("Test", "Test");

        verify(clientMock.getTransport(), times(1)).sendMessage(any(), any());
    }
}
