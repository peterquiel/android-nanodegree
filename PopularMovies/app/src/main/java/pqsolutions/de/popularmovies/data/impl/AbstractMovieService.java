package pqsolutions.de.popularmovies.data.impl;

import android.support.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.MovieSearchResult;
import pqsolutions.de.popularmovies.data.MovieService;
import pqsolutions.de.popularmovies.data.json.JsonVisitable;

import javax.inject.Inject;

/**
 * Created by pedda on 05.01.16.
 */
public class AbstractMovieService {

    @Inject
    JsonVisitable jsonVisitableProvider;

    @NonNull
    protected MovieSearchResult parseJson(String jsonResponse) throws MovieService.MovieServiceException {
        try {
            JsonVisitable visitable = this.jsonVisitableProvider.use(new JSONObject(jsonResponse));
            MovieVisitor movieVisitor = new MovieVisitor();
            TotalResultVisitor totalResultVisitor = new TotalResultVisitor();
            visitable.accept(movieVisitor);
            visitable.accept(totalResultVisitor);
            return new MovieSearchResultImpl(totalResultVisitor.getPage(), totalResultVisitor.getTotalPages(), totalResultVisitor.getTotalResults(), movieVisitor.getMovies());
        } catch (JSONException e) {
            throw new MovieService.MovieServiceException(e);
        }
    }
}
