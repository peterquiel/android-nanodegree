package pqsolutions.de.popularmovies.data;

import java.util.Date;
import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public interface Movie {

    Integer getId();

    String getTitle();

    String getOverview();

    Date getReleaseDate();

    String getBackdropPath();

    String getPosterPath();

    Double getPopularity();

    Double getVoteAverage();

    Integer getVoteCount();

    Movie addGenre(Genre genre);

    List<Genre> getGenres();
}
