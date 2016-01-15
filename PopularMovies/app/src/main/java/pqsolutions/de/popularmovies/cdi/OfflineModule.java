package pqsolutions.de.popularmovies.cdi;

import com.google.inject.AbstractModule;
import pqsolutions.de.popularmovies.data.MovieService;
import pqsolutions.de.popularmovies.data.impl.MovieServiceMock;
import pqsolutions.de.popularmovies.util.MovieImageHandler;
import pqsolutions.de.popularmovies.util.impl.MovieImageHandlerOffline;

/**
 * Created by pedda on 05.01.16.
 */
@SuppressWarnings("unused")
public class OfflineModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MovieService.class).to(MovieServiceMock.class);
        bind(MovieImageHandler.class).to(MovieImageHandlerOffline.class);
    }
}
