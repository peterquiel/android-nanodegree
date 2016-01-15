package pqsolutions.de.popularmovies.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public interface Movie extends Serializable{

    Integer getId();

    String getTitle();

    String getOverview();

    Date getReleaseDate();

    String getBackdropPath();

    String getPosterPath();

    Double getPopularity();

    Double getRating();

    Integer getVoteCount();

    Movie addGenre(Genre genre);

    List<Genre> getGenres();
}
