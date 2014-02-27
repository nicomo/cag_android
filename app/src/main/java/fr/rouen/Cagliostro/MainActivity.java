package fr.rouen.Cagliostro;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class MainActivity extends Activity {

    int epid;
    WebView wv;
    Button next;
    JSONArray data;
    String[] titles;
    String[] subtitles;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        long timestamp = prefs.getLong("timestamp", 0);
        if (timestamp == 0) {
            Date now = new Date();
            SharedPreferences.Editor editorPref = prefs.edit();
            editorPref.putLong("timestamp", now.getTime());
            editorPref.commit();
        }

        Intent intent = getIntent();
        epid = intent.getIntExtra("epid", 0);

        Resources r = getResources();
        Typeface clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");
        titles = r.getStringArray(R.array.titles);
        subtitles = r.getStringArray(R.array.subtitles);

        setContentView(R.layout.episode);

        TextView title = (TextView)findViewById(R.id.title);
        title.setText((epid+1) + ". " + titles[epid]);
        title.setTypeface(clarendon);

        TextView subtitle = (TextView)findViewById(R.id.subtitle);
        subtitle.setText(subtitles[epid]);
        subtitle.setTypeface(clarendon);

        wv = (WebView)findViewById(R.id.webView);
        if (savedInstanceState != null) {
            wv.restoreState(savedInstanceState);
        } else {
            wv.loadUrl("file:///android_asset/www/"+(epid+1)+".html");
        }
        wv.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                placePins();
            }
        });

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
            System.out.println(delayedEps);

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

    public void placePins() {
        InputStream is = getResources().openRawResource(R.raw.episodes);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        } finally {

        }

        String jsonString = writer.toString();

        try {
            JSONObject jObject = new JSONObject(jsonString);
            JSONArray data = jObject.getJSONArray("episodes");

            JSONObject jep = data.getJSONObject(epid);
            System.out.println(jep.getString("title"));
            JSONArray jpins = jep.getJSONArray("pins");

            for (int j = 0; j < jpins.length(); j++) {
                JSONObject jpin = jpins.getJSONObject(j);
                double pid = jpin.getDouble("pid");
                int cid = jpin.getInt("cid");
                String gender = jpin.getString("gender");

                System.out.println(wv.getHeight());

                RelativeLayout pinContainer = (RelativeLayout)findViewById(R.id.pinContainer);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(140, 140);
                params.topMargin = (int)(pid * wv.getHeight());
                params.leftMargin = 0;
                params.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);

                ImageButton pinButton = new ImageButton(this);
                //pinButton.setImageResource(R.drawable.pin_person_anon_male);
                //pinButton.setBackground(getResources().getDrawable(R.drawable.pin));
                int rid = gender.equals("Male") ? R.drawable.pin_person_anon_male : R.drawable.pin_person_anon_female;
                pinButton.setBackground(getResources().getDrawable(rid));

                pinContainer.addView(pinButton, params);
            }

        } catch (JSONException e) {
            System.out.println(e.getCause());
        }
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
}

