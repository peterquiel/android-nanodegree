package pqsolutions.de.popularmovies.cdi;

import android.app.Application;
import android.content.Context;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import pqsolutions.de.popularmovies.data.MovieLoaderTask;
import pqsolutions.de.popularmovies.data.MovieUrlBuilder;
import pqsolutions.de.popularmovies.data.Request;
import pqsolutions.de.popularmovies.data.impl.MovieLoaderTaskImpl;
import pqsolutions.de.popularmovies.data.impl.MovieUrlBuilderImpl;
import pqsolutions.de.popularmovies.data.impl.UrlGetRequest;
import pqsolutions.de.popularmovies.data.json.JsonVisitable;
import pqsolutions.de.popularmovies.data.json.JsonVisitableImpl;
import pqsolutions.de.popularmovies.view.MovieOverviewAdapter;

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
