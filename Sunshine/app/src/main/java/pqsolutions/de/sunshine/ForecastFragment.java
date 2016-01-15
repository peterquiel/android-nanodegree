package pqsolutions.de.sunshine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import pqsolutions.de.sunshine.data.FetchWeatherTask;
import pqsolutions.de.sunshine.data.WeatherData;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Quiel on 29.10.15.
 */
public class ForecastFragment extends Fragment {

    private CustomAdapter forcastDataAdapter;

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
            loadWeatherForecastData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forecast_main, container, false);

//        forcastDataAdapter = new CustomAdapter(getContext());
//        ListView forecastListview = (ListView) rootView.findViewById(R.id.listview_forecast);
//        forecastListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), WeatherDetailActivity.class);
//                intent.putExtra("WEATHER_DATA", ((Parcelable) forcastDataAdapter.getItem(position)));
//                startActivity(intent);
//            }
//        });
//        forecastListview.setAdapter(forcastDataAdapter);
//        loadWeatherForecastData();
//        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadWeatherForecastData();
    }

    private void loadWeatherForecastData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String unit = sharedPreferences.getString(getString(R.string.unit_preference_key), null);
        String location = sharedPreferences.getString(getString(R.string.location_preference_key), null);
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask(BuildConfig.OPEN_WEATHER_MAP_API_KEY,
                new FetchWeatherTask.Function<List<WeatherData>>() {
                    @Override
                    public void onPostExecute(List<WeatherData> result) {
                        if (forcastDataAdapter != null && result != null) {
                            forcastDataAdapter.setData(result);
                        } else {
                            Toast.makeText(getContext(), "Could not load Weatherdata", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        fetchWeatherTask.execute(unit, location);
    }

    static class CustomAdapter extends BaseAdapter {

        private static final String TAG = "CustomAdapter";
        private final Context context;
        private final LayoutInflater inflater;
        private List<WeatherData> data;

        CustomAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<WeatherData> data) {
            this.data = data;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (this.data != null) {
                return this.data.size();
            }
            return 0;
        }

        @Override
        public WeatherData getItem(int position) {
            if (this.data != null && this.data.size() > position) {
                return this.data.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (position == 0) {
                view = inflater.inflate(R.layout.list_item_forecast_today, parent, false);
                view.setTag(new ViewHolder(view));
            } else if (position == 1 || convertView == null) {
                view = inflater.inflate(R.layout.list_item_forcast, parent, false);
                view.setTag(new ViewHolder(view));
            } else {
                view = convertView;
            }
            bindData(position, view);
            return view;
        }

        private void bindData(int position, View view) {
            WeatherData weatherData = this.getItem(position);
            if (weatherData == null) {
                Log.d(TAG, String.format("getView: could not get weather data at position:%s", position));
            } else {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                viewHolder.getDateTextView().setText(view.getContext().getString(R.string.date_format, weatherData.getDay()));
                viewHolder.getForecastTextView().setText(weatherData.getDescription());
                viewHolder.getHighTextView().setText(view.getContext().getString(R.string.temperature_format, weatherData.getDayMaxTemperatur()));
                viewHolder.getLowTextView().setText(view.getContext().getString(R.string.temperature_format, weatherData.getNightTemperatur()));
            }
        }


    }
}
