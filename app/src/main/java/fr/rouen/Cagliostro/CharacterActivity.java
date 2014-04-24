package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class CharacterActivity extends Activity {

    int cid;
    JSONArray characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character);

        Intent intent = getIntent();
        cid = intent.getIntExtra("cid", 0);

        CAGApp appState = ((CAGApp)getApplicationContext());
        characters = appState.getCharacters();

        TextView name = (TextView)findViewById(R.id.name);
        try {
            name.setText(characters.getJSONObject(cid).getString("name"));
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
