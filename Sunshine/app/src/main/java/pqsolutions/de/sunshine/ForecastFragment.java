package pqsolutions.de.sunshine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Peter Quiel on 29.10.15.
 */
public class ForecastFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forcast_fragment, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            loadWeatherForcastData();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.forecast_main, container, false);

        List<String> forcastData = Arrays.asList("22.10.2015 - 18C/6C", "22.10.2015 - 18C/6C", "22.10.2015 - 18C/6C", "22.10.2015 - 18C/6C", "22.10.2015 - 18C/6C");
        ArrayAdapter<String> forcastDataAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_forcast, forcastData);
        ListView forecastListview = (ListView) rootView.findViewById(R.id.listview_forecast);
        forecastListview.setAdapter(forcastDataAdapter);

        loadWeatherForcastData();

        return rootView;
    }

    private void loadWeatherForcastData() {
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask(BuildConfig.OPEN_WEATHER_MAP_API_KEY);
        fetchWeatherTask.execute();
    }


}
