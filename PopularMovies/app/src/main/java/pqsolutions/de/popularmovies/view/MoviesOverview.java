package pqsolutions.de.popularmovies.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pqsolutions.de.popularmovies.R;

public class MoviesOverview extends Fragment {


    public MoviesOverview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_overview, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
