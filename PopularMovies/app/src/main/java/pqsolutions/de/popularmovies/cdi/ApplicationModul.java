package pqsolutions.de.popularmovies.cdi;

import com.google.inject.AbstractModule;

import pqsolutions.de.popularmovies.data.MovieLoaderTask;
import pqsolutions.de.popularmovies.data.MovieUrlBuilder;
import pqsolutions.de.popularmovies.data.MovieService;
import pqsolutions.de.popularmovies.data.Request;
import pqsolutions.de.popularmovies.data.impl.MovieDbAppService;
import pqsolutions.de.popularmovies.data.impl.MovieLoaderTaskImpl;
import pqsolutions.de.popularmovies.data.impl.MovieUrlBuilderImpl;
import pqsolutions.de.popularmovies.data.impl.UrlGetRequest;
import pqsolutions.de.popularmovies.data.json.JsonVisitable;
import pqsolutions.de.popularmovies.data.json.JsonVisitableImpl;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class ApplicationModul extends AbstractModule{

    @Override
    protected void configure() {
        bind(MovieService.class).to(MovieDbAppService.class);
        bind(Request.class).to(UrlGetRequest.class);
        bind(MovieUrlBuilder.class).to(MovieUrlBuilderImpl.class);
        bind(JsonVisitable.class).to(JsonVisitableImpl.class);
        bind(MovieLoaderTask.class).to(MovieLoaderTaskImpl.class);
    }
}
