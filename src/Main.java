import container.Container;
import movielister.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Container container = new Container();

        container.registerComponent("MovieLister", MovieLister.class);
        container.registerComponent("MovieFinder", DummyFinder.class);

        container.registerInjector(InjectMovieFinder.class, (Injector) container.lookup("MovieFinder"));
        container.registerInjector(InjectMovieFilename.class, new FinderNameInjector());

        MovieLister lister = (MovieLister) container.lookup("MovieLister");

        lister.moviesByDirector("director1");
    }
}
