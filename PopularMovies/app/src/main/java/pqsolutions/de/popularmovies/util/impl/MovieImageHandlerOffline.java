package pqsolutions.de.popularmovies.util.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.util.MovieImageHandler;
import roboguice.inject.InjectResource;

import javax.inject.Inject;

/**
 * Created by pedda on 15.01.16.
 */
public class MovieImageHandlerOffline implements MovieImageHandler {

    @InjectResource(R.drawable.overview_tmp)
    private Drawable overviewImage;

    @InjectResource(R.drawable.overview_tmp2)
    private Drawable detailImage;

    @Override
    public void putPosterInOverviewIntoTarget(Movie movie, ImageView image) {
        image.setImageDrawable(this.overviewImage);
    }

    @Override
    public void putPosterInDetailIntoTarget(Movie movie, ImageView image) {
        image.setImageDrawable(this.detailImage);

    }
}
