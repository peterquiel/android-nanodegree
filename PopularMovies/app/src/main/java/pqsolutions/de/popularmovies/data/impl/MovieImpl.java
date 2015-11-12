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

    private Integer id;
    private String title;
    private String overview;
    private Date releaseDate;
    private String backdropPath;
    private String posterPath;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private List<Genre> genres = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
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
