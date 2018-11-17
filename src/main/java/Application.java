import io.reactivex.Observable;
import notifiers.Notifiable;

import java.util.Vector;

public class Application {

    public static void main(String[] args) {
        String file = args[0];
        Parser parser = new Parser();
        parser.readFile(file);
        Vector<WebPage> webPages = parser.getWebPages();

        Observable<WebPage> observables = Observable.fromIterable(webPages);
        observables
                .repeat()
                .filter(WebPage::changed)
                .subscribe(webPage -> {
                            for (Notifiable notifier : webPage.getNotifiers()) {
                                String message = String.format("Content of %s changed.", webPage);
                                notifier.notifyChange("Page content changed", message);
                            }
                        }, Throwable::printStackTrace, () -> System.out.println("Done")
                );
    }
}
