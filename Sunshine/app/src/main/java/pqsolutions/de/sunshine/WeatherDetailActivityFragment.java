package pqsolutions.de.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import pqsolutions.de.sunshine.data.WeatherData;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherDetailActivityFragment extends Fragment {

    public WeatherDetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_weather_detail, container, false);

        if (getActivity().getIntent() != null) {
            WeatherData weatherData = getActivity().getIntent().getParcelableExtra("WEATHER_DATA");
            if (weatherData != null) {
                ((TextView) viewRoot.findViewById(R.id.list_item_date_textview)).setText(viewRoot.getContext().getString(R.string.date_format, weatherData.getDay()));
                ((TextView) viewRoot.findViewById(R.id.list_item_forecast_text_view)).setText(weatherData.getDescription());
                ((TextView) viewRoot.findViewById(R.id.list_item_low_textview)).setText(viewRoot.getContext().getString(R.string.temperature_format, weatherData.getDayMinTemperatur()));
                ((TextView) viewRoot.findViewById(R.id.list_item_high_textview)).setText(viewRoot.getContext().getString(R.string.temperature_format, weatherData.getDayMaxTemperatur()));
//                ((TextView) viewRoot.findViewById(R.id.detail_humidity_textview)).setText(viewRoot.getContext().getString(R.string.humidity_format, weatherData.getDayMaxTemperatur()));
//                ((TextView) viewRoot.findViewById(R.id.detail_wind_textview)).setText(viewRoot.getContext().getString(R.string.wind_format, weatherData.getDayMaxTemperatur()));
//                ((TextView) viewRoot.findViewById(R.id.detail_pressure_textview)).setText(viewRoot.getContext().getString(R.string.pr, weatherData.getDayMaxTemperatur()));
            }
        }
        return viewRoot;
    }
}
