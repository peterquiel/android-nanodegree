package pqsolutions.de.popularmovies.data.impl;

import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.*;
import pqsolutions.de.popularmovies.data.json.JsonVisitable;
import roboguice.inject.InjectResource;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieDbAppService implements MovieService {

    @InjectResource(R.string.theMovieDbApiKey)
    private String apiKey;

    @Inject
    private Provider<MovieUrlBuilder> movieUrlBuilderProvider;

    @Inject
    Provider<JsonVisitable> jsonVisitableProvider;

    @Inject
    private Request request;

    @Override
    public MovieSearchResult popular(int page) throws MovieServiceException {
        return handleMovieListRequest(movieUrlBuilderProvider.get().popular().page(page).language("EN").apiKey(apiKey));
    }

    @Override
    public MovieSearchResult topRated(int page) throws MovieServiceException {
        return handleMovieListRequest(movieUrlBuilderProvider.get().topRated().page(page).language("EN").apiKey(apiKey));
    }

    @Override
    public List<Genre> genre() throws MovieServiceException {
        String jsonGenreResponse = this.request.get(this.movieUrlBuilderProvider.get().genre().language("EN").apiKey(apiKey));
        try {
            JsonVisitable visitable = this.jsonVisitableProvider.get().use(new JSONObject(jsonGenreResponse));
            GenreVisitor genreVisitor = new GenreVisitor();
            visitable.accept(genreVisitor);
            return genreVisitor.getGenres();
        } catch (JSONException e) {
            throw new MovieServiceException(e);
        }
    }

    private MovieSearchResult handleMovieListRequest(String url) throws MovieServiceException {
        String jsonResponse = this.request.get(url);
        try {
            JsonVisitable visitable = this.jsonVisitableProvider.get().use(new JSONObject(jsonResponse));
            MovieVisitor movieVisitor = new MovieVisitor();
            TotalResultVisitor totalResultVisitor = new TotalResultVisitor();
            visitable.accept(movieVisitor);
            visitable.accept(totalResultVisitor);
            return new MovieSearchResultImpl(totalResultVisitor.getPage(), totalResultVisitor.getTotalPages(), totalResultVisitor.getTotalResults(), movieVisitor.getMovies());
        } catch (JSONException e) {
            throw new MovieServiceException(e);
        }
    }


}
