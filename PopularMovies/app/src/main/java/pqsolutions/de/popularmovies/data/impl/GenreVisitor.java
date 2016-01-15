package pqsolutions.de.popularmovies.data.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.Genre;
import pqsolutions.de.popularmovies.data.json.JsonVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedda on 12.11.15.
 */
public class GenreVisitor implements JsonVisitor {

    private List<Genre> genres = new ArrayList<>();

    private GenreImpl genre = new GenreImpl();


    @Override
    public void handle(String key, Object value) {

    }

    @Override
    public void handle(String key, String value) {
        if ("name".equals(key)) {
            this.genre.setName(value);
        }
    }

    @Override
    public void handle(String key, Boolean value) {

    }

    @Override
    public void handle(String key, Integer value) {
        if ("id".equals(key)) {
            this.genre.setId(value);
        }
    }

    @Override
    public void handle(String key, Long value) {

    }

    @Override
    public void handle(String key, Double value) {

    }

    @Override
    public void handle(String key, JSONObject value) throws JSONException {
        if (!value.isNull("id") && !value.isNull("name")) {
            this.genre = new GenreImpl();
            this.genres.add(this.genre);
        }
    }

    @Override
    public void handle(String key, JSONArray value) throws JSONException {

    }

    public List<Genre> getGenres() {
        return genres;
    }
}
