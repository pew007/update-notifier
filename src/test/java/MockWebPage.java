import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MockWebPage extends WebPage {

    MockWebPage(String url) throws IOException {
        super(url);
    }

    /**
     *  Override url factory method to return mock objects for testing.
     *  Mocked URLConnection will always return +1 day as lastModifiedDate
     *  To mock final classes such as URL, see
     *  https://github.com/mockito/mockito/wiki/What's-new-in-Mockito-2#mock-the-unmockable-opt-in-mocking-of-final-classesmethods
     */
    @Override
    URL url(String url) throws IOException {
        URL mockURL = mock(URL.class);
        URLConnection mockConnection = mock(URLConnection.class);

        Date futureDate = lastModifiedDate == null ? new Date() : lastModifiedDate;
        Calendar c = Calendar.getInstance();
        c.setTime(futureDate);
        c.add(Calendar.DATE, 1);
        futureDate = c.getTime();

        when(mockConnection.getLastModified()).thenReturn(futureDate.getTime());
        when(mockURL.openConnection()).thenReturn(mockConnection);

        return mockURL;
    }
}
