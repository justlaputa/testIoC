package movielister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laputa on 11/12/15.
 */
public class DummyFinder implements MovieFinder, InjectMovieFilename, Injector {
    private String movieFilename;

    @Override
    public List<Movie> findAll() {
        return new ArrayList<Movie>() {{
            add(new Movie("movie1", "direct1"));
            add(new Movie("movie2", "direct2"));
        }};
    }

    @Override
    public void inject(Object object) {
        ((InjectMovieFinder) object).injectMovieFinder(this);
    }

    @Override
    public void injectFilename(String filename) {
        this.movieFilename = filename;
    }
}
