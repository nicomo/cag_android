package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.etsy.android.grid.ExtendableListView;
import com.etsy.android.grid.StaggeredGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends Activity {

    JSONArray characters;
    JSONArray episodes;
    JSONArray places;
    boolean episodesExpanded = false;
    boolean charExpanded = false;
    SharedPreferences prefs;
    Timer timer;
    EpisodeCardAdapter eca;
    CharacterCardAdapter cca;
    PlaceAdapter pla;
    MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CAGApp appState = ((CAGApp)getApplicationContext());
        characters = appState.getCharacters();
        episodes = appState.getEpisodes();
        places = appState.getPlaces();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final long timestamp = prefs.getLong("timestamp", 0);
        if (timestamp == 0) {
            Date now = new Date();
            SharedPreferences.Editor editorPref = prefs.edit();
            editorPref.putLong("timestamp", now.getTime());
            editorPref.commit();
            
            Intent help = new Intent(this, HelpModal.class);
            startActivity(help);
        }
        Date now = new Date();
        final double minElapsed = ( now.getTime() - timestamp ) / (60000.0*5.0);
        final Boolean delayedEps = prefs.getBoolean("delayedEps", true);

        setContentView(R.layout.home);

        Typeface clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");
        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");

        TextView journal = (TextView)findViewById(R.id.journal);
        journal.setTypeface(clarendon);

        TextView author = (TextView)findViewById(R.id.author);
        author.setTypeface(georgia);

        TextView title = (TextView)findViewById(R.id.title);
        title.setTypeface(clarendon);

        TextView characterstitle = (TextView)findViewById(R.id.characterstitle);
        characterstitle.setTypeface(clarendon);

        TextView placestitle = (TextView)findViewById(R.id.placestitle);
        placestitle.setTypeface(clarendon);

        StaggeredGridView episodesgrid = (StaggeredGridView) findViewById(R.id.episodesgrid);
        eca = new EpisodeCardAdapter(this, episodes);
        episodesgrid.setAdapter(eca);

        final Context context = this;

        episodesgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position <= minElapsed || ! delayedEps) {
                    Intent intent = new Intent(context, EpisodeActivity.class);
                    intent.putExtra("epid", position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }
        });

        map = (MapView)findViewById(R.id.map);
        pla = new PlaceAdapter(this, places);
        map.setAdapter(pla);

        GridView charactersgrid = (GridView)findViewById(R.id.charactersgrid);

        charactersgrid.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    return true;
                }
                return false;
            }
        });

        charactersgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (charpublished(position)) {
                    Intent intent = new Intent(context, CharacterActivity.class);
                    intent.putExtra("cid", position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }
        });
        cca = new CharacterCardAdapter(this, characters);
        charactersgrid.setAdapter(cca);

        timer = new Timer("refreshEpisodesAndCharactersAndPlaces");
        timer.schedule(_refreshEpisodesAndCharactersAndPlaces, 0, 5000);
    }

    public boolean charpublished(int cid) {
        long timestamp = this.prefs.getLong("timestamp", 0);
        Boolean delayedEps = this.prefs.getBoolean("delayedEps", true);
        Date now = new Date();
        final double minElapsed = ( now.getTime() - timestamp ) / (60000.0*5.0);

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

    public int epidforplid(int plid) {
        CAGApp appState = (CAGApp)getApplicationContext();
        JSONArray places = appState.getPlaces();

        try {
            return places.getJSONObject(plid).getInt("epid");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public boolean placepublished(int plid) {
        long timestamp = this.prefs.getLong("timestamp", 0);
        Boolean delayedEps = this.prefs.getBoolean("delayedEps", true);
        Date now = new Date();
        final double minElapsed = ( now.getTime() - timestamp ) / (60000.0*5.0);

        return ! delayedEps || epidforplid(plid) < minElapsed;
    }

    private final TimerTask _refreshEpisodesAndCharactersAndPlaces = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    eca.notifyDataSetChanged();
                    cca.notifyDataSetChanged();
                    pla = new PlaceAdapter(HomeActivity.this, places);
                    map.setAdapter(pla);
                }
            });
        }
    };

    public void toggleEpisodes(View view) {
        final StaggeredGridView v = (StaggeredGridView)findViewById(R.id.episodesgrid);
        final double d = v.getContext().getResources().getDisplayMetrics().density;
        final double wp = v.getContext().getResources().getDisplayMetrics().widthPixels;
        final double w = wp/d;
        final double ratio = v.getContext().getResources().getInteger(R.integer.episodes_columns) == 4 ? 3.8 : 1.75;
        final int h = (int)(w*ratio);

        if (episodesExpanded == false) {
            final int initsize = 460;
            final int targetsize = h;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? (int) (targetsize * d)
                            : (int) ((initsize * d) + ((targetsize - initsize) * d * interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    TextView t = (TextView) findViewById(R.id.ep_expander_label);
                    t.setText("CACHER LES EPISODES");

                    ImageView i = (ImageView) findViewById(R.id.ep_expander_icon);
                    i.setImageResource(R.drawable.expand_up);

                    episodesExpanded = true;
                }
            });

            // 1dp/ms
            a.setDuration((int) (500 / d));
            v.startAnimation(a);
        }
        else
        {
            final int initsize = h;
            final int targetsize = 460;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? (int) (targetsize * d)
                            : (int) ((initsize * d) + ((targetsize - initsize) * d * interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    TextView t = (TextView) findViewById(R.id.ep_expander_label);
                    t.setText("TOUS LES EPISODES");

                    ImageView i = (ImageView) findViewById(R.id.ep_expander_icon);
                    i.setImageResource(R.drawable.expand_down);

                    episodesExpanded = false;
                }
            });

            // 1dp/ms
            a.setDuration((int) (500 / d));
            v.startAnimation(a);
        }
    }

    public void toggleCharacters(View view) {
        final GridView v = (GridView)findViewById(R.id.charactersgrid);
        final double d = v.getContext().getResources().getDisplayMetrics().density;

        if (charExpanded == false) {
            final int initsize = 320;
            final int targetsize = 720;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? (int) (targetsize * d)
                            : (int) ((initsize * d) + ((targetsize - initsize) * d * interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    TextView t = (TextView) findViewById(R.id.char_expander_label);
                    t.setText("CACHER LES PERSONNAGES");

                    ImageView i = (ImageView) findViewById(R.id.char_expander_icon);
                    i.setImageResource(R.drawable.expand_up);

                    charExpanded = true;
                }
            });

            // 1dp/ms
            a.setDuration((int) (500 / d));
            v.startAnimation(a);
        }
        else
        {
            final int initsize = 1120;
            final int targetsize = 320;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? (int) (targetsize * d)
                            : (int) ((initsize * d) + ((targetsize - initsize) * d * interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    TextView t = (TextView) findViewById(R.id.char_expander_label);
                    t.setText("TOUS LES PERSONNAGES");

                    ImageView i = (ImageView) findViewById(R.id.char_expander_icon);
                    i.setImageResource(R.drawable.expand_down);

                    charExpanded = false;
                }
            });

            // 1dp/ms
            a.setDuration((int) (500 / d));
            v.startAnimation(a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem buttonSettings = menu.add(R.string.settings_title);
        buttonSettings.setIcon(android.R.drawable.ic_menu_preferences);
        buttonSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        buttonSettings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
                HomeActivity.this.startActivity(settingsIntent);
                return false;
            }
        });
        return true;
    }

    public void playVideo(View view) {
        int vid = 0;
        switch (view.getId()) {
            case (R.id.button1):
                vid = 1;
                break;
            case (R.id.button2):
                vid = 2;
                break;
            case (R.id.button3):
                vid = 3;
                break;
            case (R.id.button4):
                vid = 4;
                break;
        }
        System.out.println("Playing : " + vid);
        Intent va = new Intent(this, VideoActivity.class);
        va.putExtra("vid", vid);
        startActivity(va);
    }

    public void gotoCredits(View view) {
        Intent ca = new Intent(this, CreditsActivity.class);
        startActivity(ca);
    }
}
