import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

class WebPage {

    private URL address;
    Date lastModifiedDate;

    WebPage(String url) throws IOException {
        this.address = url(url);
        this.lastModifiedDate = null;
    }

    URL url(String url) throws IOException {
        return new URL(url);
    }

    boolean changed() throws IOException {
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
}
