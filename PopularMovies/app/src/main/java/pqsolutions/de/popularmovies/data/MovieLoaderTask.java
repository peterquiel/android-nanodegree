package pqsolutions.de.popularmovies.data;

import android.os.AsyncTask;
import pqsolutions.de.popularmovies.util.Function;

/**
 * Created by pedda on 05.01.16.
 */
public interface MovieLoaderTask {

    enum Params {
        TOP_RATED, MOST_POPULAR;
    }

    void loadMovies(Params params);

    /**
     * Sets the callback function when that should be called when the task has been executed.
     * <p/>
     * When an error occured during movie data loading an {@link IllegalStateException} will be thrown when invoking <code>executeWhenMoviesLoaded</code>.
     *
     * @param executeWhenMoviesLoaded
     */
    void setOnLoadFinishedHandler(Function<MovieSearchResult, Void> executeWhenMoviesLoaded);

    void setErrorHandler(Function<MovieService.MovieServiceException, Void> executeWhenErrorOccurred);
}
