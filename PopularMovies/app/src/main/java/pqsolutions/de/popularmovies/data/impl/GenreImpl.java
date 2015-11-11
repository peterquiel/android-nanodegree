package pqsolutions.de.popularmovies.data.impl;

import pqsolutions.de.popularmovies.data.Genre;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class GenreImpl implements Genre {

    private final int id;
    private final String name;

    public GenreImpl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
