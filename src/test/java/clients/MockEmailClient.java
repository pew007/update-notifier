package clients;

import javax.mail.Transport;

import static org.mockito.Mockito.*;

class MockEmailClient extends EmailClient {

    MockEmailClient() {
        super();
    }

    @Override
    Transport transport() {
        try {
            Transport mockTransport = mock(Transport.class);
            doNothing().when(mockTransport).sendMessage(any(), any());

            return mockTransport;
        } catch (Exception exception) {
            return null;
        }
    }
}
