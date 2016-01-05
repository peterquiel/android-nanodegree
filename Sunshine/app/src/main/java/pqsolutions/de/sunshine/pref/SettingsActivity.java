package pqsolutions.de.sunshine.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.util.Log;
import pqsolutions.de.sunshine.R;

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_view);
        findPreference(getString(R.string.location_preference_key)).setOnPreferenceChangeListener(this);
        findPreference(getString(R.string.unit_preference_key)).setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (getString(R.string.unit_preference_key).equals(preference.getKey()) && newValue != null) {
            Log.d(TAG, "Unit set to: " + newValue);
        } else if (getString(R.string.location_preference_key).equals(preference.getKey())) {
            Log.d(TAG, "Unit set to: " + newValue);
        }
        return true;
    }
}

