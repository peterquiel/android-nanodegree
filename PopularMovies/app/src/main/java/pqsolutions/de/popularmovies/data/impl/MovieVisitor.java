package pqsolutions.de.popularmovies.data.impl;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.data.json.JsonVisitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedda on 12.11.15.
 */
public class MovieVisitor implements JsonVisitor {
    private static final String logTag = MovieVisitor.class.getSimpleName();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<Movie> movies = new ArrayList<>();
    private MovieImpl movie;


    @Override
    public void handle(String key, Object value) {

    }

    @Override
    public void handle(String key, String value) {
        if ("title".equals(key)) {
            this.movie.setTitle(value);
        } else if ("backdrop_path".equals(key)) {
            this.movie.setBackdropPath(value);
        } else if ("release_date".equals(key)) {
            try {
                this.movie.setReleaseDate(dateFormat.parse(value));
            } catch (ParseException e) {
                Log.e(logTag, String.format("Could parser String %s to Date ", value));
            }
        } else if ("overview".equals(key)) {
            this.movie.setOverview(value);
        } else if ("poster_path".equals(key)) {
            this.movie.setPosterPath(value);
        }
    }

    @Override
    public void handle(String key, Boolean value) {

    }

    @Override
    public void handle(String key, Integer value) {
        if ("id".equals(key)) {
            this.movie.setId(value);
        } else if ("vote_count".equals(key)) {
            this.movie.setVoteCount(value);
        }
    }

    @Override
    public void handle(String key, Long value) {

    }

    @Override
    public void handle(String key, Double value) {
        if ("vote_average".equals(key)) {
            this.movie.setVoteAverage(value);
        } else if ("popularity".equals(key)) {
            this.movie.setPopularity(value);
        }
    }

    @Override
    public void handle(String key, JSONObject value) throws JSONException {
        if (!value.isNull("title") && !value.isNull("overview")){
            movie = new MovieImpl();
            this.movies.add(this.movie);
        }
    }

    @Override
    public void handle(String key, JSONArray value) throws JSONException {
        if ("genre_ids".equals(key)) {
            for (int i = 0; i < value.length(); i++) {
                this.movie.addGenre(new GenreImpl().setId(value.getInt(i)));
            }
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
