package pqsolutions.de.popularmovies.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import pqsolutions.de.popularmovies.R;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import javax.inject.Inject;

@ContentView(R.layout.activity_movie)
public class MovieDetailActivity extends RoboActionBarActivity  {

    @Inject
    private MovieDetailFragment movieDetailFragment;

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(this.toolbar);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.container_placeholder, movieDetailFragment).commit();
        }
    }
}
