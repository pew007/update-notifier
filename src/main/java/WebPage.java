import notifiers.Notifiable;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class WebPage {

    private URL address;
    private Set<Notifiable> notifiers;
    Date lastModifiedDate;
    int currentContentLength;

    WebPage(String url) throws IOException {
        this.address = url(url);
        this.lastModifiedDate = null;
        this.currentContentLength = 0;
        this.notifiers = new HashSet<>();
    }

    URL url(String url) throws IOException {
        return new URL(url);
    }

    void addNotifier(Notifiable notifier) {
        this.notifiers.add(notifier);
    }

    Set<Notifiable> getNotifiers() {
        return this.notifiers;
    }

    boolean changed() throws IOException {
        return lastModifiedDateChanged() || contentLengthChanged();
    }

    private boolean lastModifiedDateChanged() throws IOException {
        URL address = this.address;
        URLConnection connect = address.openConnection();

        long time = connect.getLastModified();
        Date modifiedDate = new Date(time);

        if (lastModifiedDate == null) {
            lastModifiedDate = modifiedDate;
            return false;
        }

        if (modifiedDate.compareTo(lastModifiedDate) > 0) {
            lastModifiedDate = modifiedDate;
            return true;
        }

        return false;
    }

    private boolean contentLengthChanged() throws IOException {
        URL address = this.address;
        URLConnection connection = address.openConnection();

        int contentLength = connection.getContentLength();

        if (currentContentLength == 0) {
            currentContentLength = contentLength;
            return false;
        }

        if (currentContentLength != contentLength) {
            currentContentLength = contentLength;
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WebPage) {
            WebPage toCompare = (WebPage) obj;
            return address.equals(toCompare.address);
        }

        return false;
    }

    @Override
    public String toString() {
        return address.toString();
    }
}
