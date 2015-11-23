import container.Container;
import movielister.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Container container = new Container();

        container.registerComponent("MovieLister", MovieLister.class);
        container.registerComponent("MovieFinder", DummyFinder.class);

        container.registerInjector(InjectMovieFinder.class, (Injector) container.lookup("MovieFinder"));
        container.registerInjector(InjectMovieFilename.class, new FinderNameInjector());

        container.start();

        MovieLister lister = (MovieLister) container.lookup("MovieLister");

        List<Movie> movies = lister.moviesByDirector("director1");

        movies.forEach(movie -> {
            System.out.println(movie.Name);
        });
    }
}
