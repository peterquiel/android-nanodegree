package pqsolutions.de.popularmovies.data.impl;

import pqsolutions.de.popularmovies.data.Genre;
import pqsolutions.de.popularmovies.data.MovieSearchResult;
import pqsolutions.de.popularmovies.data.MovieService;

import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieServiceMock implements MovieService {

    @Override
    public MovieSearchResult popular(int page) {
        return null;
    }

    @Override
    public MovieSearchResult topRated(int page) {
        return null;
    }

    @Override
    public List<Genre> genre() throws MovieServiceException {
        return null;
    }
}
