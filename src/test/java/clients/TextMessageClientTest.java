package clients;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TextMessageClientTest {

    @Test
    void send() throws MessagingException {
        MockTextMessageClient client = new MockTextMessageClient();

        List<String> recipients = new ArrayList<>();
        recipients.add("6262285115@tmomail.net");
        client.send("subject", "test message", recipients);

        verify(client.emailClient.transport, times(1)).sendMessage(any(), any());
    }
}
