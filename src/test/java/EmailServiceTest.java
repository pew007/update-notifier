import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    private EmailService emailService;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
    }

    @Test
    void generateMessage() throws MessagingException, IOException {
        String subject = "test";
        String body = "<h1>This is a test</h1>";
        String[] recipients = {"test@test.com"};

        MimeMessage message = emailService.generateMessage(subject, body, recipients);

        Assertions.assertNotNull(message);
        Assertions.assertEquals(subject, message.getSubject());
        Assertions.assertEquals(body, message.getContent());
        Assertions.assertEquals(1, message.getAllRecipients().length);
        Assertions.assertEquals("text/html", message.getContentType());
    }

    @Test
    void send() throws MessagingException {
        String subject = "test";
        String body = "<h1>This is a test</h1>";
        String[] recipients = {"pyxwang@gmail.com"};

        MimeMessage message = emailService.generateMessage(subject, body, recipients);
        emailService.send(message);
    }
}
