package fr.rouen.Cagliostro;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.content.Intent;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;
import java.util.Date;

public class MainActivity extends Activity {

    int epid;
    WebView wv;
    Button next;
    String[] titles;
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

        setContentView(R.layout.episode);

        TextView title = (TextView)findViewById(R.id.title);
        title.setText((epid+1) + ". " + titles[epid]);
        title.setTypeface(clarendon);

        TextView subtitle = (TextView)findViewById(R.id.subtitle);
        //subtitle.setText(r.getString(R.string.ep1_subtitle));
        subtitle.setTypeface(clarendon);

        wv = (WebView)findViewById(R.id.webView);
        if (savedInstanceState != null) {
            wv.restoreState(savedInstanceState);
        } else {
            wv.loadUrl("file:///android_asset/www/"+(epid+1)+".html");
        }

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

