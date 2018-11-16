import notifiers.Notifiable;
import notifiers.NotifierFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;

class Parser {

    private Vector<WebPage> webPages = new Vector<>();

    Vector<WebPage> getWebPages() {
        return webPages;
    }

    void readFile(String file) {
        try {
            Stream<String> lines = Files.lines(Paths.get(file), StandardCharsets.UTF_8);
            lines.forEachOrdered(line -> {
                try {
                    parseLine(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void parseLine(String line) throws IOException {
        String[] segments = line.trim().toLowerCase().split(" ");
        String url = segments[0];
        String notifyMethod = segments[1];
        String[] args = java.util.Arrays.stream(segments, 2, segments.length).toArray(String[]::new);

        WebPage webPage = new WebPage(url);
        Notifiable notifier = NotifierFactory.create(notifyMethod, args);

        if (!webPages.contains(webPage)) {
            webPages.add(webPage);
        }

        webPages.get(webPages.indexOf(webPage)).addNotifier(notifier);
    }
}
