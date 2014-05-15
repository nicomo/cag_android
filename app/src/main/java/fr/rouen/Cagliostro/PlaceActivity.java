package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class PlaceActivity extends Activity {

    int plid;
    JSONArray places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);

        Intent intent = getIntent();
        plid = intent.getIntExtra("plid", 0);

        CAGApp appState = ((CAGApp)getApplicationContext());
        places = appState.getPlaces();

        TextView name = (TextView)findViewById(R.id.name);
        try {
            getActionBar().setTitle(places.getJSONObject(plid).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
