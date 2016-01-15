package pqsolutions.de.popularmovies.data.impl;

import android.net.Uri;
import pqsolutions.de.popularmovies.data.MovieUrlBuilder;

/**
 * Created by pedda on 26.11.15.
 */
public class MovieUrlBuilderImpl implements MovieUrlBuilder, MovieUrlBuilder.CommonParameterBuilder, MovieUrlBuilder.PageableBuilder {

    private Uri.Builder builder;

    public MovieUrlBuilderImpl() {
        initBuilder();
    }

    private void initBuilder() {
        this.builder = new Uri.Builder();
        this.builder.scheme("https").authority("api.themoviedb.org")
                .appendPath("3");
    }

    @Override
    public PageableBuilder topRated() {
        this.builder.appendPath("movie").appendPath("top_rated");
        return this;
    }

    @Override
    public CommonParameterBuilder genre() {
        this.builder.appendPath("genre").appendPath("movie").appendPath("list");
        return this;
    }

    @Override
    public PageableBuilder popular() {
        this.builder.appendPath("movie").appendPath("popular");
        return this;
    }

    @Override
    public CommonParameterBuilder language(String language) {
        this.builder.appendQueryParameter("language", language);
        return this;
    }

    @Override
    public CommonParameterBuilder page(int pageNumber) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Only numbers greater than zero are allowed as parameter");
        }
        this.builder.appendQueryParameter("page", pageNumber + "");
        return this;
    }

    @Override
    public String apiKey(String apiKey) {
        try {
            return this.builder.appendQueryParameter("api_key", apiKey).build().toString();
        } finally {
            initBuilder();
        }
    }
}
