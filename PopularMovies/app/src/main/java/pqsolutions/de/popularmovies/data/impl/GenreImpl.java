package pqsolutions.de.popularmovies.data.impl;

import pqsolutions.de.popularmovies.data.Genre;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class GenreImpl implements Genre {

    private int id;
    private String name;

    public GenreImpl setId(int id) {
        this.id = id;
        return this;
    }

    public GenreImpl setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
