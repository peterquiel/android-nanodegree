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
public class MovieLoaderTaskImpl extends AsyncTask<MovieLoaderTask.Params, Void, MovieSearchResult> implements MovieLoaderTask {

    private static final String TAG = MovieLoaderTaskImpl.class.getSimpleName();

    @Inject
    MovieService movieService;

    private Function<MovieSearchResult, Void> executeWhenMoviesLoaded;
    private MovieService.MovieServiceException exceptionDuringDataLoading;
    private Function<MovieService.MovieServiceException, Void> executeWhenErrorOccurred;

    @Override
    protected MovieSearchResult doInBackground(Params... params) {
        try {
            switch (params != null && params.length == 0 ? Params.MOST_POPULAR : params[0]) {
                case MOST_POPULAR:
                    return this.movieService.popular(0);
                default:
                    return this.movieService.topRated(0);
            }
        } catch (MovieService.MovieServiceException e) {
            exceptionDuringDataLoading = e;
            Log.d(TAG, "Could not load movies from movie service. Returning null", e);
        }
        return null;
    }

    @Override
    public void loadMovies(Params params) {
        execute(params);
    }

    @Override
    public void setOnLoadFinishedHandler(Function<MovieSearchResult, Void> executeWhenMoviesLoaded) {
        this.executeWhenMoviesLoaded = executeWhenMoviesLoaded;
    }

    @Override
    public void setErrorHandler(Function<MovieService.MovieServiceException, Void> executeWhenErrorOccurred) {
        this.executeWhenErrorOccurred = executeWhenErrorOccurred;
    }

    @Override
    protected void onPostExecute(MovieSearchResult movieSearchResult) {
        try {
            if (this.executeWhenMoviesLoaded != null) {
                if (this.exceptionDuringDataLoading != null) {
                    if (this.executeWhenErrorOccurred != null) {
                        this.executeWhenErrorOccurred.apply(this.exceptionDuringDataLoading);
                    } else {
                        Log.d(TAG, "Error occurred during data loading but no error handler was set");
                    }
                } else {
                    this.executeWhenMoviesLoaded.apply(movieSearchResult);
                }
            } else {
                Log.d(TAG, "onPostExecute: no function set, please set function callback before loading movies!");
            }
        } finally {
            this.exceptionDuringDataLoading = null;
        }
    }
}
