package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class CharacterActivity extends Activity {

    int cid;
    JSONArray characters;
    Typeface clarendon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character);

        clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");

        Intent intent = getIntent();
        cid = intent.getIntExtra("cid", 0);

        CAGApp appState = ((CAGApp)getApplicationContext());
        characters = appState.getCharacters();

        TextView name = (TextView)findViewById(R.id.name);
        ImageView avatar = (ImageView)findViewById(R.id.avatar);
        try {
            name.setText(characters.getJSONObject(cid).getString("name"));
            name.setTypeface(clarendon);
            avatar.setImageResource(this.getResources().getIdentifier("pin_" + cid, "drawable", this.getPackageName()));
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
