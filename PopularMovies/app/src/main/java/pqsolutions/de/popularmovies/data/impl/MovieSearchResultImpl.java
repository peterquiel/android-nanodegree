package pqsolutions.de.popularmovies.data.impl;

import java.util.ArrayList;
import java.util.List;

import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.data.MovieSearchResult;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieSearchResultImpl implements MovieSearchResult {

    private final int totalPages;
    private final int page;
    private final int totalResults;
    private final List<Movie> movies = new ArrayList<>();

    public MovieSearchResultImpl(int totalPages, int page, int totalResults) {
        this.totalPages = totalPages;
        this.page = page;
        this.totalResults = totalResults;
    }

    @Override
    public int totalPages() {
        return this.totalPages;
    }

    @Override
    public int page() {
        return this.page;
    }

    @Override
    public int totalResults() {
        return this.totalResults;
    }

    @Override
    public List<Movie> movies() {
        return this.movies;
    }
}
