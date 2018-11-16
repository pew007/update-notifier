import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Application {

    public static void main(String[] args) {
        String file = args[0];
        Parser parser = new Parser();
        parser.readFile(file);
        Vector<WebPage> webPages = parser.getWebPages();
    }
}
