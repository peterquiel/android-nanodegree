package pqsolutions.de.popularmovies.cdi;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import pqsolutions.de.popularmovies.data.MovieService;
import pqsolutions.de.popularmovies.data.MovieUrlBuilder;
import pqsolutions.de.popularmovies.data.Request;
import pqsolutions.de.popularmovies.data.impl.MovieDbAppService;
import pqsolutions.de.popularmovies.data.impl.MovieUrlBuilderImpl;
import pqsolutions.de.popularmovies.data.impl.UrlGetRequest;
import pqsolutions.de.popularmovies.util.MovieImageHandler;
import pqsolutions.de.popularmovies.util.impl.MovieImageHandlerOffline;
import pqsolutions.de.popularmovies.util.impl.MovieImageHandlerOnline;

/**
 * Created by pedda on 05.01.16.
 */
@SuppressWarnings("unused")
public class OnlineModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Request.class).to(UrlGetRequest.class);
        bind(MovieUrlBuilder.class).to(MovieUrlBuilderImpl.class);
        bind(MovieService.class).to(MovieDbAppService.class);
        bind(MovieImageHandler.class).to(MovieImageHandlerOnline.class);
    }
}
