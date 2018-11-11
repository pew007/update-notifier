import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WebPageTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void changed() throws IOException {
        MockWebPage webPage = new MockWebPage("https://www.google.com");

        Assertions.assertTrue(webPage.changed());
    }
}