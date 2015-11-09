package pqsolutions.de.myappportfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ChooseApplication extends AppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_application, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startSpotifyStreamerApp(View view) {
        showToast(R.string.launch_spotify_app);
    }

    public void startFootballScoresApp(View view) {
        showToast(R.string.launch_football_app);
    }

    public void startLibraryApp(View view) {
        showToast(R.string.launch_lib_app);
    }

    public void startBuildItBiggerApp(View view) {
        showToast(R.string.launch_build_it_bigger_app);
    }

    public void startMyCapstoneApp(View view) {
        showToast(R.string.launch_my_capstone_app);
    }

    private void showToast(int toastMessageResId) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), toastMessageResId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(toastMessageResId);
        }
        toast.show();
    }
}
