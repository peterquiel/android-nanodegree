package pqsolutions.de.sunshine.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import pqsolutions.de.sunshine.json.JsonVisitableImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by Peter Quiel on 30.10.15.
 */
public class WeatherDataParser {

    private static final String TAG = WeatherDataParser.class.getSimpleName();

    public List<WeatherData> parseWeatherData(String weatherJsonString, Date dateOfRequest) {
        try {
            WeatherDataVisitor weatherDataVisitor = new WeatherDataVisitor(dateOfRequest);
            new JsonVisitableImpl().use(new JSONObject(weatherJsonString)).accept(weatherDataVisitor);
            return weatherDataVisitor.getWeatherDataList();
        } catch (Exception e) {
            Log.e(TAG, String.format("Could not parse json string: %s", weatherJsonString), e);
            return null;
        }
    }
}

