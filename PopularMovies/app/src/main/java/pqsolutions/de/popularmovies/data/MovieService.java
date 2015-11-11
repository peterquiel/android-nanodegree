package pqsolutions.de.popularmovies.data;

import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public interface MovieService {

    MovieSearchResult popular(int page);

    MovieSearchResult topRated(int page);

}
