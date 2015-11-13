package movielister;

/**
 * Created by laputa on 11/12/15.
 */
public class FinderNameInjector implements Injector {
    @Override
    public void inject(Object object) {
        ((InjectMovieFilename) object).injectFilename("movies.txt");
    }
}
