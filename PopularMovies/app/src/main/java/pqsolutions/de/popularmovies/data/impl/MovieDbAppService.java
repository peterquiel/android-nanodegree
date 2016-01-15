package pqsolutions.de.popularmovies.data.impl;

import android.support.annotation.NonNull;
import com.google.inject.Provider;
import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.*;
import pqsolutions.de.popularmovies.data.json.JsonVisitable;
import roboguice.inject.InjectResource;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieDbAppService extends AbstractMovieService implements MovieService {

    @InjectResource(R.string.theMovieDbApiKey)
    private String apiKey;

    @Inject
    private MovieUrlBuilder movieUrlBuilderProvider;

    @Inject
    private Request request;

    @Override
    public MovieSearchResult popular(int page) throws MovieServiceException {
        return handleMovieListRequest(movieUrlBuilderProvider.popular().page(page).apiKey(apiKey));
    }

    @Override
    public MovieSearchResult topRated(int page) throws MovieServiceException {
        return handleMovieListRequest(movieUrlBuilderProvider.topRated().page(page).apiKey(apiKey));
    }

    @Override
    public List<Genre> genre() throws MovieServiceException {
        String jsonGenreResponse = this.request.get(this.movieUrlBuilderProvider.genre().language("EN").apiKey(apiKey));
        try {
            JsonVisitable visitable = this.jsonVisitableProvider.use(new JSONObject(jsonGenreResponse));
            GenreVisitor genreVisitor = new GenreVisitor();
            visitable.accept(genreVisitor);
            return genreVisitor.getGenres();
        } catch (JSONException e) {
            throw new MovieServiceException(e);
        }
    }

    private MovieSearchResult handleMovieListRequest(String url) throws MovieServiceException {
        return parseJson(this.request.get(url));
    }

}
