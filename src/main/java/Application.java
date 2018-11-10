import io.reactivex.Observable;

public class Application {

    public static void main(String[] args) {
        Observable.just("Hello World").subscribe(line -> System.out.println(line));

        Observable.range(0, 10).subscribe(integer -> System.out.println(integer + 10));
    }
}
