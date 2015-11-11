package pqsolutions.de.popularmovies.data;

import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public interface MovieSearchResult {

    int totalPages();

    int page();

    int totalResults();

    List<Movie> movies();

}
