package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Date;

public class CharacterActivity extends Activity {

    int cid;
    JSONArray characters;
    JSONArray friends;
    Typeface clarendon;
    Typeface georgia;
    FriendCardAdapter cca;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");
        georgia = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");

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

        final Context context = this;

        TextView myfriends = (TextView)findViewById(R.id.myfriends);
        myfriends.setTypeface(georgia);

        GridView friendsgrid = (GridView)findViewById(R.id.friendsgrid);

        friendsgrid.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    return true;
                }
                return false;
            }
        });

        friendsgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //if (charpublished(position)) {
                    Intent intent = new Intent(context, CharacterActivity.class);
                    intent.putExtra("cid", position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                //}
            }
        });

        try {
            friends = characters.getJSONObject(cid).getJSONArray("friends");
            cca = new FriendCardAdapter(this, characters, friends);
            friendsgrid.setAdapter(cca);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public boolean charpublished(int cid) {
        long timestamp = this.prefs.getLong("timestamp", 0);
        Boolean delayedEps = this.prefs.getBoolean("delayedEps", true);
        Date now = new Date();
        final double minElapsed = ( now.getTime() - timestamp ) / 60000.0;

        return ! delayedEps || epidforcid(cid) < minElapsed;
    }

    public int epidforcid(int cid) {
        CAGApp appState = ((CAGApp)getApplicationContext());
        JSONArray characters = appState.getCharacters();

        try {
            return characters.getJSONObject(cid).getInt("epid");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
