package pqsolutions.de.popularmovies.data.impl;

import android.app.Application;
import android.test.ApplicationTestCase;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.Genre;
import pqsolutions.de.popularmovies.data.json.JsonVisitableImpl;

import java.util.List;

/**
 * Created by pedda on 12.11.15.
 */
public class GenerVisitorTests extends ApplicationTestCase<Application> {


    public GenerVisitorTests() {
        super(Application.class);
    }

    public void test_deserializing_genre() throws Exception{
        String jsonString = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("smal_genre.json"));
        GenereVisitor visitor = new GenereVisitor();
        new JsonVisitableImpl().use(new JSONObject(jsonString)).accept(visitor);

        List<Genre> genres = visitor.getGenres();
        assertNotNull(genres);
        assertEquals(2, genres.size());

        Genre actionGenre = genres.get(0);
        assertEquals(28, actionGenre.getId());
        assertEquals("Action", actionGenre.getName());

        Genre adventureGenre = genres.get(1);
        assertEquals(12, adventureGenre.getId());
        assertEquals("Adventure", adventureGenre.getName());
    }
}
