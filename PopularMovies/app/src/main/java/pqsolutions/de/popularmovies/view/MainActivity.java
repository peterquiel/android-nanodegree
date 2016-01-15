package pqsolutions.de.popularmovies.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.MovieLoaderTask;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import javax.inject.Inject;

@ContentView(R.layout.activity_movie)
public class MainActivity extends RoboActionBarActivity {

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    private SharedPreferences sharedPreferences;

    @InjectResource(R.string.movieSortingPreferenceKey)
    private String movieSortingPreferenceKey;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.popular_movie_settings) {
            updateSortingPreference(MovieLoaderTask.Params.MOST_POPULAR);
            return true;
        } else if (id == R.id.top_rated_settings) {
            updateSortingPreference(MovieLoaderTask.Params.TOP_RATED);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateSortingPreference(MovieLoaderTask.Params sorting) {
        this.sharedPreferences.edit().putInt(this.movieSortingPreferenceKey, sorting.getKey()).apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            this.getFragmentManager().beginTransaction().replace(R.id.container_placeholder, new MovieOverviewFragment()).commit();
        }
    }
}
