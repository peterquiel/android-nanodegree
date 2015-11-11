package pqsolutions.de.popularmovies.data.impl;

import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.MovieSearchResult;
import pqsolutions.de.popularmovies.data.MovieService;
import roboguice.inject.InjectResource;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieDbAppService implements MovieService {

    @InjectResource(R.string.theMovieDbApiKey)
    private String apiKey;


    @Override
    public MovieSearchResult popular(int page) {
//        https://api.themoviedb.org/3/movie/popular?api_key=xx
        return null;
    }

    @Override
    public MovieSearchResult topRated(int page) {
        //        https://api.themoviedb.org/3/movie/top_rated?api_key=

        return null;
    }
}
