package pqsolutions.de.popularmovies.data.impl;

import android.os.AsyncTask;
import android.util.Log;
import com.google.inject.Inject;
import pqsolutions.de.popularmovies.data.MovieLoaderTask;
import pqsolutions.de.popularmovies.data.MovieSearchResult;
import pqsolutions.de.popularmovies.data.MovieService;
import pqsolutions.de.popularmovies.util.Function;

/**
 * Created by pedda on 05.01.16.
 */
public class MovieLoaderTaskImpl implements MovieLoaderTask {

    private static final String TAG = MovieLoaderTaskImpl.class.getSimpleName();

    @Inject
    MovieService movieService;

    private Function<MovieSearchResult, Void> executeWhenMoviesLoaded;

    private MovieService.MovieServiceException exceptionDuringDataLoading;

    private Function<MovieService.MovieServiceException, Void> executeWhenErrorOccurred;

    @Override
    public void loadMovies(Params params) {
        new BackgroundLoader().execute(params);
    }

    @Override
    public void setOnLoadFinishedHandler(Function<MovieSearchResult, Void> executeWhenMoviesLoaded) {
        this.executeWhenMoviesLoaded = executeWhenMoviesLoaded;
    }

    @Override
    public void setErrorHandler(Function<MovieService.MovieServiceException, Void> executeWhenErrorOccurred) {
        this.executeWhenErrorOccurred = executeWhenErrorOccurred;
    }

    class BackgroundLoader extends AsyncTask<MovieLoaderTask.Params, Void, MovieSearchResult> {

        @Override
        protected MovieSearchResult doInBackground(Params... params) {
            try {
                switch (params[0] == null ? Params.MOST_POPULAR : params[0]) {
                    case MOST_POPULAR:
                        return movieService.popular(1);
                    default:
                        return movieService.topRated(1);
                }
            } catch (MovieService.MovieServiceException e) {
                exceptionDuringDataLoading = e;
                Log.d(TAG, "Could not load movies from movie service. Returning null", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(MovieSearchResult movieSearchResult) {
            try {
                if (executeWhenMoviesLoaded != null) {
                    if (exceptionDuringDataLoading != null) {
                        if (executeWhenErrorOccurred != null) {
                            executeWhenErrorOccurred.apply(exceptionDuringDataLoading);
                        } else {
                            Log.d(TAG, "Error occurred during data loading but no error handler was set");
                        }
                    } else {
                        executeWhenMoviesLoaded.apply(movieSearchResult);
                    }
                } else {
                    Log.d(TAG, "onPostExecute: no function set, please set function callback before loading movies!");
                }
            } finally {
                exceptionDuringDataLoading = null;
            }
        }
    }
}
