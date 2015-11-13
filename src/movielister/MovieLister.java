package movielister;

import java.util.List;

/**
 * Created by laputa on 11/12/15.
 */
public class MovieLister implements InjectMovieFinder {

    private MovieFinder finder;

    public List<Movie> moviesByDirector(String director) {
        return finder.findAll();
    }

    @Override
    public void injectMovieFinder(MovieFinder finder) {
        this.finder = finder;
    }
}
