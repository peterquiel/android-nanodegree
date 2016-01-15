package pqsolutions.de.popularmovies.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.data.MovieLoaderTask;
import pqsolutions.de.popularmovies.data.MovieSearchResult;
import pqsolutions.de.popularmovies.util.Function;
import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import javax.inject.Inject;
import java.util.ArrayList;


public class MovieOverviewFragment extends RoboFragment implements Function<MovieSearchResult, Void>, SharedPreferences.OnSharedPreferenceChangeListener, AdapterView.OnItemClickListener {

    public static final String MOVIE_LIST_BUNDLE_KEY = "movieList";

    @Inject
    private SharedPreferences sharedPreferences;

    @InjectResource(R.string.movieSortingPreferenceKey)
    private String movieSortingPreferenceKey;

    @InjectResource(R.string.movieDataExtra)
    private String movieDataExtra;

    @Inject
    private MovieLoaderTask movieLoaderTask;

    @Inject
    private MovieOverviewAdapter movieOverviewAdapter;

    @InjectView(R.id.movie_overview_grid)
    private GridView movieOverviewGrid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_overview, container, false);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieOverviewGrid.setAdapter(this.movieOverviewAdapter);
        movieOverviewGrid.setOnItemClickListener(this);
        movieLoaderTask.setOnLoadFinishedHandler(this);
        if (savedInstanceState == null) {
            loadMovieData();
        } else {
            ArrayList movieList = savedInstanceState.getParcelableArrayList(MOVIE_LIST_BUNDLE_KEY);
            if (movieList != null) {
                this.movieOverviewAdapter.setMovieList(movieList);
            }
        }
    }


    private void loadMovieData() {
        MovieLoaderTask.Params sorting = MovieLoaderTask.Params.fromInt(sharedPreferences.getInt(movieSortingPreferenceKey, 1));
        movieLoaderTask.loadMovies(sorting);
    }

    @Override
    public Void apply(MovieSearchResult parameter) {
        this.movieOverviewAdapter.setMovieList((ArrayList<Movie>) parameter.movies());
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOVIE_LIST_BUNDLE_KEY, this.movieOverviewAdapter.getMovieList());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (this.movieSortingPreferenceKey.equals(key)) {
            this.loadMovieData();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(movieOverviewGrid.getContext(), MovieDetailActivity.class);
        intent.putExtra(movieDataExtra, movieOverviewAdapter.getItem(position));
        startActivity(intent);
    }
}
