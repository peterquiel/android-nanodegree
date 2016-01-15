package pqsolutions.de.popularmovies.data.impl;

import android.app.Application;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.Genre;
import pqsolutions.de.popularmovies.data.MovieSearchResult;
import pqsolutions.de.popularmovies.data.MovieService;
import roboguice.util.Ln;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieServiceMock extends AbstractMovieService implements MovieService {

    @Inject
    private Application context;

    @Override
    public MovieSearchResult popular(int page) throws MovieServiceException {
        return this.loadMoviesFromFile();
    }

    @Override
    public MovieSearchResult topRated(int page) throws MovieServiceException {
        return this.loadMoviesFromFile();
    }


    @Override
    public List<Genre> genre() throws MovieServiceException {
        return null;
    }

    private MovieSearchResult loadMoviesFromFile() throws MovieServiceException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.popular);
        if (inputStream != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder jsonString = new StringBuilder();
                do {
                    line = reader.readLine();
                    jsonString.append(line).append('\n');
                }
                while (line != null);
                return this.parseJson(jsonString.toString());
            } catch (IOException e) {
                Ln.d(e, "Could not read file");
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Ln.d(e, "Cannot read popular resource");
                }
            }
        } else {
            Ln.d("Could not read popular raw file");
        }
        return null;
    }
}
