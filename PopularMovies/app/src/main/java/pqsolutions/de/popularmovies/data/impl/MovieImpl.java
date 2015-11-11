package pqsolutions.de.popularmovies.data.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pqsolutions.de.popularmovies.data.Genre;
import pqsolutions.de.popularmovies.data.Movie;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieImpl implements Movie {

    private final Integer id;
    private final String title;
    private final String overview;
    private final Date releaseDate;
    private final String backdropPath;
    private final String posterPath;
    private final Double popularity;
    private final Double voteAverage;
    private final Integer voteCount;
    private final List<Genre> genres = new ArrayList<>();

    public MovieImpl(Integer id, String title, String overview, Date releaseDate, String backdropPath, String posterPath, Double popularity, Double voteAverage, Integer voteCount) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public Double getPopularity() {
        return popularity;
    }

    @Override
    public Double getVoteAverage() {
        return voteAverage;
    }

    @Override
    public Integer getVoteCount() {
        return voteCount;
    }

    @Override
    public Movie addGenre(Genre genre) {
        this.addGenre(genre);
        return this;
    }

    @Override
    public List<Genre> getGenres() {
        return genres;
    }
}
