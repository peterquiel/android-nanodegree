package pqsolutions.de.popularmovies.cdi;

import com.google.inject.AbstractModule;

import pqsolutions.de.popularmovies.data.MovieService;
import pqsolutions.de.popularmovies.data.impl.MovieDbAppService;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class ApplicationModul extends AbstractModule{

    @Override
    protected void configure() {
        bind(MovieService.class).to(MovieDbAppService.class);
    }
}
