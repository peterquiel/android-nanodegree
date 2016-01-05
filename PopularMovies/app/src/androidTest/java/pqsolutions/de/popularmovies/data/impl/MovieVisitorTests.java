package pqsolutions.de.popularmovies.data.impl;

import android.app.Application;
import android.test.ApplicationTestCase;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.data.json.JsonVisitableImpl;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by pedda on 12.11.15.
 */
public class MovieVisitorTests extends ApplicationTestCase<Application> {


    private final SimpleDateFormat dateFormat;
    private MovieVisitor visitor;

    public MovieVisitorTests() {
        super(Application.class);
        dateFormat = new SimpleDateFormat("ddMMyyyy");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String jsonString = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("smal_popular_movie.json"));
        visitor = new MovieVisitor();
        new JsonVisitableImpl().use(new JSONObject(jsonString)).accept(visitor);

    }

    public void test_movie_deserializer() throws Exception {
        List<Movie> movies = visitor.getMovies();
        assertNotNull(movies);
        assertEquals(2, movies.size());

        Movie spectre = movies.get(0);
        assertEquals("Spectre", spectre.getTitle());
        assertEquals(((Integer) 206647), spectre.getId());
        assertTrue(spectre.getOverview().startsWith("A cryptic message from"));
        assertEquals(49.48668, spectre.getPopularity());
        assertEquals(6.7, spectre.getVoteAverage());
        assertEquals(((Integer) 394), spectre.getVoteCount());
        assertEquals("/1n9D32o30XOHMdMWuIT4AaA5ruI.jpg", spectre.getPosterPath());
        assertEquals("/wVTYlkKPKrljJfugXN7UlLNjtuJ.jpg", spectre.getBackdropPath());
        assertEquals("06112015", dateFormat.format(spectre.getReleaseDate()));

        Movie jurassicWorld = movies.get(1);
        assertEquals("Jurassic World", jurassicWorld.getTitle());
        assertEquals(((Integer) 135397), jurassicWorld.getId());
        assertEquals("/dkMD5qlogeRMiEixC4YNPUvax2T.jpg", jurassicWorld.getBackdropPath());
        assertTrue(jurassicWorld.getOverview().startsWith("Twenty-two years after the events"));
        assertEquals(47.576869, jurassicWorld.getPopularity());
        assertEquals("/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg", jurassicWorld.getPosterPath());
        assertEquals(6.9, jurassicWorld.getVoteAverage());
        assertEquals(((Integer) 2932), jurassicWorld.getVoteCount());
        assertEquals("12062015", dateFormat.format(jurassicWorld.getReleaseDate()));
    }

    public void test_if_genres_are_added_with_id_only() {
        Movie spectre = this.visitor.getMovies().get(0);
        assertEquals(3, spectre.getGenres().size());
        assertEquals(28, spectre.getGenres().get(0).getId());
        assertEquals(12, spectre.getGenres().get(1).getId());
        assertEquals(80, spectre.getGenres().get(2).getId());

        Movie jurassicWorld = this.visitor.getMovies().get(1);
        assertEquals(4, jurassicWorld.getGenres().size());
        assertEquals(28, jurassicWorld.getGenres().get(0).getId());
        assertEquals(12, jurassicWorld.getGenres().get(1).getId());
        assertEquals(878, jurassicWorld.getGenres().get(2).getId());
        assertEquals(53, jurassicWorld.getGenres().get(3).getId());
    }
}
