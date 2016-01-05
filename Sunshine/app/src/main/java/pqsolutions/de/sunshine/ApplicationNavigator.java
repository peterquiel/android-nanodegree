package pqsolutions.de.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import pqsolutions.de.sunshine.pref.SettingsActivity;

/**
 * Created by pedda on 26.12.15.
 */
public class ApplicationNavigator {

    private final Activity currentActivity;

    private static final String TAG = ApplicationNavigator.class.getSimpleName();

    public ApplicationNavigator(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public boolean handleMenuItem(MenuItem item) {
        //noinspection SimplifiableIfStatement
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this.currentActivity, SettingsActivity.class);
            this.currentActivity.startActivity(intent);
            return true;
        } else if (id == R.id.show_current_location) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.currentActivity);
            String location = sharedPreferences.getString(this.currentActivity.getString(R.string.location_preference_key), null);
            Uri geoUri = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", location).build();
            Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
            if (intent.resolveActivity(this.currentActivity.getPackageManager()) != null) {
                this.currentActivity.startActivity(intent);
            } else {
                Log.e(TAG, String.format("Could not start intent for uri '%s'", geoUri.toString()));
                Toast.makeText(this.currentActivity, "Could not show location, no App to show location", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}
