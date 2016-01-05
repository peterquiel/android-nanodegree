package pqsolutions.de.sunshine.data;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.sunshine.json.JsonVisitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pedda on 22.12.15.
 */
public class WeatherDataVisitor implements JsonVisitor{

    private static final String TAG = WeatherDataVisitor.class.getSimpleName();

    private List<WeatherData> weatherDataList;
    private WeatherData weatherData;
    private final Calendar calendar;

    public WeatherDataVisitor(Date currentDate) {
        calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

    }

    @Override
    public void handle(String key, Object value) {

    }

    @Override
    public void handle(String key, String value) {
        if (this.weatherData == null) {
            return;
        }
        if ("description".equals(key)) {
            weatherData.setDescription(value);
        }
    }

    @Override
    public void handle(String key, Boolean value) {

    }

    @Override
    public void handle(String key, Integer value) {
    }

    @Override
    public void handle(String key, Long value) {
    }

    @Override
    public void handle(String key, Double value) {
        if (this.weatherData == null) {
            return;
        }
        if ("day".equals(key)) {
            this.weatherData.setDayTemperatur(value);
        }
        if ("min".equals(key)) {
            this.weatherData.setDayMinTemperatur(value);
        }
        if ("max".equals(key)) {
            this.weatherData.setDayMaxTemperatur(value);
        }
        if ("night".equals(key)) {
            this.weatherData.setNightTemperatur(value);
        }
    }

    @Override
    public void handle(String key, JSONObject value) throws JSONException {
        Log.d(TAG, String.format("Object parsing for : %s", key));
        if (key != null && key.startsWith("list:") && this.weatherDataList != null) {
            this.weatherData= new WeatherData();
            this.weatherDataList.add(weatherData);
            this.weatherData.setDay(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Override
    public void handle(String key, JSONArray value) throws JSONException {
        Log.d(TAG, String.format("Array parsing for : %s", key));
        if ("list".equals(key)) {
            this.weatherDataList = new ArrayList<>();
        }
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }
}
