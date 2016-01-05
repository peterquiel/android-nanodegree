package pqsolutions.de.sunshine.data;


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter Quiel on 29.10.15.
 */
public class FetchWeatherTask extends AsyncTask<String, Void, List<WeatherData>> {

    public static final String LOG_TAG = FetchWeatherTask.class.getName();
    private final String openWeatherApiKey;
    private final Function<List<WeatherData>> function;

    public FetchWeatherTask(String openWeatherApiKey, Function<List<WeatherData>> function) {
        this.openWeatherApiKey = openWeatherApiKey;
        this.function = function;
    }

    @Override
    protected List<WeatherData> doInBackground(String... params) {
        String unit = "metric";
        String location = "Paderborn";
        if (params != null && params.length == 2) {
            if (params[0] != null) {
                unit = params[0];
            }
            if (params[1] != null) {
                location = params[1];
            }
        }
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("http").authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("forecast")
                .appendPath("daily")
                .appendQueryParameter("q", location)
                .appendQueryParameter("cnt", "7")
                .appendQueryParameter("units", unit)
                .appendQueryParameter("mode", "json")
                .appendQueryParameter("appId", this.openWeatherApiKey).build();
        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            Log.d(LOG_TAG, String.format("Weather forecast url: %s", uri.toString()));
            URL url = new URL(uri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            return new WeatherDataParser().parseWeatherData(buffer.toString(), new Date());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
    }

    @Override
    protected void onPostExecute(List<WeatherData> result) {
        if (this.function != null) {
            this.function.onPostExecute(result);
        } else {
            Log.e(LOG_TAG, "Could not invoke function because its null");
        }
    }

    public interface Function<T> {
        void onPostExecute(T result);
    }
}
