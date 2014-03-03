package fr.rouen.Cagliostro;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.content.Intent;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;
import java.util.Date;

public class MainActivity extends Activity implements ScrollViewListener {

    int epid;
    CAGScrollView sv;
    CAGWebView wv;
    Button next;
    JSONArray data;
    String[] titles;
    String[] subtitles;
    SharedPreferences prefs;
    boolean pinplaced = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        epid = intent.getIntExtra("epid", 0);

        CAGApp appState = ((CAGApp)getApplicationContext());
        data = appState.getJSONArray();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        long timestamp = prefs.getLong("timestamp", 0);
        if (timestamp == 0) {
            Date now = new Date();
            SharedPreferences.Editor editorPref = prefs.edit();
            editorPref.putLong("timestamp", now.getTime());
            editorPref.commit();
        }

        Resources r = getResources();
        Typeface clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");
        titles = r.getStringArray(R.array.titles);
        subtitles = r.getStringArray(R.array.subtitles);

        setContentView(R.layout.episode);

        sv = (CAGScrollView)findViewById(R.id.scrollView);
        sv.setScrollViewListener(this);

        TextView title = (TextView)findViewById(R.id.title);
        title.setText((epid+1) + ". " + titles[epid]);
        title.setTypeface(clarendon);

        TextView subtitle = (TextView)findViewById(R.id.subtitle);
        subtitle.setText(subtitles[epid]);
        subtitle.setTypeface(clarendon);

        wv = (CAGWebView)findViewById(R.id.webView);
        wv.loadUrl("file:///android_asset/www/"+(epid+1)+".html");

        VideoView vv=(VideoView)findViewById(R.id.videoView);
        vv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg));
        vv.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        vv.start();

        next = (Button)findViewById(R.id.nextButton);
        next.setTypeface(clarendon);
        next.setLineSpacing(0, 1.3f);
        next.setText("Episode "+(epid+1)+"\nA paraitre");

        Timer t = new Timer("refreshButton");
        t.schedule(_refreshButton, 0, 500);
    }

    private final TimerTask _refreshButton = new TimerTask() {
        @Override
        public void run() {
            long timestamp = prefs.getLong("timestamp", 0);
            Boolean delayedEps = prefs.getBoolean("delayedEps", true);

            Date now = new Date();
            final double minElapsed = ( now.getTime() - timestamp ) / 60000.0;

            if (epid+1 > minElapsed && delayedEps) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        next.setText(String.format("Episode %d\nA praitre dans %5.2f minutes", epid + 2, epid + 1 - minElapsed));
                    }
                });
            }
            else
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        next.setText("Episode "+(epid+2)+"\n" + titles[epid+1]);
                        next.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                nextEpisode(v);
                            }
                        });
                    }
                });
                this.cancel();
            }
        }
    };

    public void placePins(int wvh) {

        if (pinplaced)
            return;

        System.out.println("placePins for " + wvh);

        try {
            JSONObject jep = data.getJSONObject(epid);
            JSONArray jpins = jep.getJSONArray("pins");

            System.out.println(jep.toString());

            for (int j = 0; j < jpins.length(); j++) {
                JSONObject jpin = jpins.getJSONObject(j);
                double pid = jpin.getDouble("pid");
                final int cid = jpin.getInt("cid");
                String gender = jpin.getString("gender");

                RelativeLayout pinContainer = (RelativeLayout)findViewById(R.id.pinContainer);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
                params.topMargin = (int)(pid * wvh);
                params.leftMargin = 0;
                params.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);

                final ImageButton pinButton = new ImageButton(this);
                int rid = gender.equals("Male") ? R.drawable.pin_male : R.drawable.pin_female;
                pinButton.setImageResource(rid);
                pinButton.setAdjustViewBounds(true);
                pinButton.setBackgroundResource(0);

                pinContainer.addView(pinButton, params);

                pinButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        gotoCharacter(v, cid);
                    }
                });

                jpin.put("pinButton", pinButton);
                jpin.put("flipped", false);
            }

        } catch (JSONException e) {
            System.out.println(e.getCause());
        }

        pinplaced = true;
    }

    public void gotoCharacter(View view, int cid) {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("cid", cid);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void nextEpisode(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("epid", epid+1);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        //finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem buttonSettings = menu.add(R.string.settings_title);
        buttonSettings.setIcon(android.R.drawable.ic_menu_preferences);
        buttonSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        buttonSettings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(settingsIntent);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onScrollChanged(CAGScrollView scrollView, int x, int y, int oldx, int oldy) {
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);

        try {
            JSONObject jep = data.getJSONObject(epid);
            JSONArray jpins = jep.getJSONArray("pins");

            for (int j = 0; j < jpins.length(); j++) {
                JSONObject jpin = jpins.getJSONObject(j);
                double pid = jpin.getDouble("pid");
                final int cid = jpin.getInt("cid");
                String gender = jpin.getString("gender");
                final ImageButton pinButton = (ImageButton)jpin.get("pinButton");
                boolean flipped = jpin.getBoolean("flipped");

                if (pinButton.getGlobalVisibleRect(scrollBounds) && !flipped) {
                    jpin.put("flipped", true);

                    Animation flip_part1 = AnimationUtils.loadAnimation(this, R.anim.flip_part1);
                    final Animation flip_part2 = AnimationUtils.loadAnimation(this, R.anim.flip_part2);

                    final Context ctx = this;

                    flip_part1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            try {
                                int iden = ctx.getResources().getIdentifier("pin_" + cid, "drawable", ctx.getPackageName());
                                pinButton.setImageResource(iden);
                                pinButton.startAnimation(flip_part2);
                            } catch (Exception e) {
                                System.out.println(e.getCause());
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    pinButton.startAnimation(flip_part1);
                }
            }

        } catch (JSONException e) {
            System.out.println(e.getCause());
        }
    }
}

