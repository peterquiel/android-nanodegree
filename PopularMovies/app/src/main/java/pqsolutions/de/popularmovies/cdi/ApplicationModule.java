package pqsolutions.de.popularmovies.cdi;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import pqsolutions.de.popularmovies.data.MovieLoaderTask;
import pqsolutions.de.popularmovies.data.impl.MovieLoaderTaskImpl;
import pqsolutions.de.popularmovies.data.json.JsonVisitable;
import pqsolutions.de.popularmovies.data.json.JsonVisitableImpl;

/**
 * Created by Peter Quiel on 11.11.15.
 */
@SuppressWarnings("unused")
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(JsonVisitable.class).toProvider(JsonVisitableProvider.class);
        bind(MovieLoaderTask.class).to(MovieLoaderTaskImpl.class);
    }

    static class JsonVisitableProvider implements Provider<JsonVisitable> {
        @Override
        public JsonVisitable get() {
            return new JsonVisitableImpl();
        }
    }
}
