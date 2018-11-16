package clients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EmailClientTest {

    private EmailClient emailService;

    @BeforeEach
    void setUp() {
        emailService = new EmailClient();
    }

    @Test
    void generateMessage() throws MessagingException, IOException {
        String subject = "test";
        String body = "<h1>This is a test</h1>";
        List<String> recipients = new ArrayList<>();
        recipients.add("test@test.com");

        MimeMessage message = emailService.generateMessage(subject, body, recipients);

        Assertions.assertNotNull(message);
        Assertions.assertEquals(subject, message.getSubject());
        Assertions.assertEquals(body, message.getContent());
        Assertions.assertEquals(1, message.getAllRecipients().length);
        Assertions.assertEquals("text/plain", message.getContentType());
    }

    @Test
    void send() throws MessagingException {
        String subject = "test";
        String body = "This is a test";
        List<String> recipients = new ArrayList<>();
        recipients.add("pyxwang@gmail.com");

        emailService.send(subject, body, recipients);
    }
}
