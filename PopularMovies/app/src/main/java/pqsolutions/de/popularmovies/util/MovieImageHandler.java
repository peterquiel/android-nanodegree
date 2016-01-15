package pqsolutions.de.popularmovies.util;

import android.widget.ImageView;
import pqsolutions.de.popularmovies.data.Movie;

/**
 * Created by pedda on 15.01.16.
 */
public interface MovieImageHandler {
    void putPosterInOverviewIntoTarget(Movie movie, ImageView image);

    void putPosterInDetailIntoTarget(Movie movie, ImageView image);
}
