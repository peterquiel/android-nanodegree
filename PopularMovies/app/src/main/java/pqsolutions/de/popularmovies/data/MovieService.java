package pqsolutions.de.popularmovies.data;

import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public interface MovieService {

    MovieSearchResult popular(int page) throws MovieServiceException;

    MovieSearchResult topRated(int page) throws MovieServiceException;

    List<Genre> genre() throws MovieServiceException;

    public static class MovieServiceException extends Exception {

        public MovieServiceException() {
        }

        public MovieServiceException(String detailMessage) {
            super(detailMessage);
        }

        public MovieServiceException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public MovieServiceException(Throwable throwable) {
            super(throwable);
        }
    }
}
