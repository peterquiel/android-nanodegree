package pqsolutions.de.popularmovies.data.impl;

import android.app.Application;
import android.test.ApplicationTestCase;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.json.JsonVisitableImpl;

/**
 * Created by pedda on 26.11.15.
 */
public class TotalResultVisitorTests extends ApplicationTestCase<Application> {

    private TotalResultVisitor visitor;

    public TotalResultVisitorTests() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        String jsonString = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("smal_popular_movie.json"));
        visitor = new TotalResultVisitor();
        new JsonVisitableImpl().use(new JSONObject(jsonString)).accept(visitor);
    }

    public void test_the_visitor(){
        assertEquals(1, visitor.getPage());
        assertEquals(12231, visitor.getTotalPages());
        assertEquals(244603, visitor.getTotalResults());
    }
}
