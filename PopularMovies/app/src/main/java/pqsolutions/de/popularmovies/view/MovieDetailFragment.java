package pqsolutions.de.popularmovies.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.util.MovieImageHandler;
import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import javax.inject.Inject;

public class MovieDetailFragment extends RoboFragment {

    @InjectResource(R.string.movieDataExtra)
    private String movieDataExtra;

    @InjectView(R.id.movie_original_title)
    private TextView originalTitleView;

    @InjectView(R.id.movie_release_date)
    private TextView releaseDateView;

    @InjectView(R.id.movie_plot_synopsis)
    private TextView plotSynopsisView;

    @InjectView(R.id.movie_vote_count)
    private TextView voteCountView;

    @InjectView(R.id.movie_rating)
    private TextView ratingView;

    @InjectView(R.id.movie_poster_image)
    private ImageView posterImage;

    @Inject
    private Context context;

    @Inject
    private MovieImageHandler movieImageHandler;

    private Movie movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.movie = savedInstanceState.getParcelable("movie");
        } else {
            final Intent intent = getActivity().getIntent();
            if (intent != null) {
                movie = intent.getParcelableExtra(this.movieDataExtra);
            } else {
                movie = null;
            }
        }
        return inflater.inflate(R.layout.fragment_movie_detailed, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (movie != null) {
            this.originalTitleView.setText(movie.getTitle());
            this.plotSynopsisView.setText(movie.getOverview());
            this.releaseDateView.setText(context.getString(R.string.movieDetailReleaseDate, movie.getReleaseDate()));
            this.ratingView.setText(context.getString(R.string.movieDetailRating, movie.getRating()));
            this.voteCountView.setText(context.getString(R.string.movieDetailVotes, movie.getVoteCount()));
            this.movieImageHandler.putPosterInDetailIntoTarget(movie, this.posterImage);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie", this.movie);
        this.movie = null;
    }
}