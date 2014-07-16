package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import fr.rouen.Cagliostro.provider.ZipFileContentProvider;


public class CharacterActivity extends FragmentActivity {

    int cid;
    JSONArray characters;
    JSONArray friends;
    JSONArray confessions;
    JSONArray messages;
    Typeface clarendon;
    Typeface georgia;
    FriendCardAdapter cca;
    ConfessionsAdapter confa;
    MessagesAdapter messa;
    SharedPreferences prefs;
    PhotosAdapter pha;

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
        confessions = appState.getConfessions();
        messages = appState.getMessages();

        LinearLayout charbg = (LinearLayout)findViewById(R.id.charbg);

        try {
            ZipResourceFile expansionFile = APKExpansionSupport
                    .getAPKExpansionZipFile(this, 10, 0);
            InputStream fileStream = expansionFile.getInputStream("char/char_" + cid + "_bg.png");
            charbg.setBackground(Drawable.createFromStream(fileStream, "char/char_" + cid + "_bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        try {
            ViewPager photopager = (ViewPager)findViewById(R.id.photospager);
            pha = new PhotosAdapter(super.getSupportFragmentManager(), characters.getJSONObject(cid).getInt("numphotos"), this, cid, "char/char");
            photopager.setAdapter(pha);

            CirclePageIndicator ind = (CirclePageIndicator)findViewById(R.id.photospagerindicator);
            ind.setViewPager(photopager);
            ind.setFillColor(Color.parseColor("#151313"));
            ind.setPageColor(Color.parseColor("#beb2b0"));
            ind.setStrokeWidth(0);
            ind.setRadius(8);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                try {
                    int nextcid = characters.getJSONObject(cid).getJSONArray("friends").getInt(position);
                    if (charpublished(nextcid)) {
                        Intent intent = new Intent(context, CharacterActivity.class);
                        intent.putExtra("cid", nextcid);
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            friends = characters.getJSONObject(cid).getJSONArray("friends");
            cca = new FriendCardAdapter(this, characters, cid, friends);
            friendsgrid.setAdapter(cca);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView confessionstitle = (TextView)findViewById(R.id.confessionstitle);
        confessionstitle.setTypeface(georgia);

        TextView messagestitle = (TextView)findViewById(R.id.messagestitle);
        messagestitle.setTypeface(georgia);

        ViewPager confessionspager = (ViewPager)findViewById(R.id.confessionspager);
        confa = new ConfessionsAdapter(super.getSupportFragmentManager(), confessions, cid);
        confessionspager.setAdapter(confa);

        ViewPager messagespager = (ViewPager)findViewById(R.id.messagespager);
        messa = new MessagesAdapter(super.getSupportFragmentManager(), messages, cid);
        messagespager.setAdapter(messa);

        CirclePageIndicator confindicator = (CirclePageIndicator)findViewById(R.id.confessionspagerindicator);
        confindicator.setViewPager(confessionspager);
        confindicator.setFillColor(Color.parseColor("#151313"));
        confindicator.setPageColor(Color.parseColor("#beb2b0"));
        confindicator.setStrokeWidth(0);
        confindicator.setRadius(8);

        CirclePageIndicator messindicator = (CirclePageIndicator)findViewById(R.id.messagespagerindicator);
        messindicator.setViewPager(messagespager);
        messindicator.setFillColor(Color.parseColor("#151313"));
        messindicator.setPageColor(Color.parseColor("#beb2b0"));
        messindicator.setStrokeWidth(0);
        messindicator.setRadius(8);
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
        final double minElapsed = ( now.getTime() - timestamp ) / (60000.0*60.0*24.0);

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
