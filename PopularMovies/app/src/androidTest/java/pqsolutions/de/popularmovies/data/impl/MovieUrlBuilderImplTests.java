package pqsolutions.de.popularmovies.data.impl;

import android.app.Application;
import android.test.ApplicationTestCase;
import pqsolutions.de.popularmovies.data.MovieUrlBuilder;

/**
 * Created by pedda on 26.11.15.
 */
public class MovieUrlBuilderImplTests extends ApplicationTestCase<Application> {

    private MovieUrlBuilder movieUrlBuilder;

    public MovieUrlBuilderImplTests() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        movieUrlBuilder = new MovieUrlBuilderImpl();
    }

    public void test_if_popular_url_is_generated() {
        String url = this.movieUrlBuilder.popular().page(10).language("FR").apiKey("123");
        assertEquals("https://api.themoviedb.org/3/movie/popular?page=10&language=FR&api_key=123", url);
    }

    public void test_if_top_rated_url_is_generated() {
        String url = this.movieUrlBuilder.topRated().page(10).language("FR").apiKey("123");
        assertEquals("https://api.themoviedb.org/3/movie/top_rated?page=10&language=FR&api_key=123", url);
    }

    public void test_if_genere_url_is_generated() {
        String url = this.movieUrlBuilder.genre().language("FR").apiKey("123");
        assertEquals("https://api.themoviedb.org/3/genre/movie/list?language=FR&api_key=123", url);
    }
}
