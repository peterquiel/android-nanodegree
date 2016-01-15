package pqsolutions.de.popularmovies.util.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.util.MovieImageHandler;
import roboguice.inject.InjectResource;

import javax.inject.Inject;

/**
 * Created by pedda on 15.01.16.
 */
public class MovieImageHandlerOnline implements MovieImageHandler {

    @InjectResource(R.integer.posterOverviewSize)
    private Integer posterOverviewSize;

    @InjectResource(R.integer.posterDetailSize)
    private Integer posterDetailSize;

    @Inject
    private Context context;

    @Override
    public void putPosterInOverviewIntoTarget(Movie movie, ImageView image) {
        Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context).load(context.getString(R.string.imagesUrl, posterOverviewSize, movie.getPosterPath())).into(image);
    }

    @Override
    public void putPosterInDetailIntoTarget(Movie movie, final ImageView image) {
        Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context).load(context.getString(R.string.imagesUrl, posterDetailSize, movie.getPosterPath())).into(image);
    }
}
