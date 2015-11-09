package pqsolutions.de.sunshine;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Peter Quiel on 30.10.15.
 */
public class WeatherDataParser {


    private static final String LOG_TAG = WeatherDataParser.class.getName();

    public static double getMaxTemperatureForDay(String weatherJsonString, int dayIndex) {
        try {
            JSONObject jsonObject = new JSONObject(weatherJsonString);
            JSONObject day = (JSONObject) ((JSONArray) (jsonObject.get("list"))).get(dayIndex);
            return (double) ((JSONObject)(day.get("temp"))).get("max");
        } catch (Exception e) {
            Log.e(LOG_TAG, String.format("Could not parse json string: %s", weatherJsonString), e);
            return 0;
        }
    }
}
