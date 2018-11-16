import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private String testFile;

    @BeforeEach
    void setUp() {
        testFile = "/Users/pwang/Projects/update-notifier/src/test/resources/input.txt";
    }

    @Test
    void readFile() {
        Parser parser = new Parser();
        parser.readFile(testFile);

        Assertions.assertNotNull(parser.getWebPages());
    }
}
