package pqsolutions.de.popularmovies.data;

import pqsolutions.de.popularmovies.util.Function;

/**
 * Created by pedda on 05.01.16.
 */
public interface MovieLoaderTask {

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

    enum Params {
        TOP_RATED(1), MOST_POPULAR(2);

        private final int key;

        Params(int key) {
            this.key = key;
        }

        public static Params fromInt(Integer key) {
            if (key != null) {
                for (Params params : values()) {
                    if (params.getKey() == key) {
                        return params;
                    }
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }
    }
}
