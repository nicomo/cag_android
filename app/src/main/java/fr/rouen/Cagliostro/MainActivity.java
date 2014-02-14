package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.content.Intent;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends Activity {

    int epid;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        epid = intent.getIntExtra("epid", 1);

        Resources r = getResources();
        Typeface clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");
        String[] titles = r.getStringArray(R.array.titles);

        setContentView(R.layout.episode);

        TextView title = (TextView)findViewById(R.id.title);
        title.setText(epid + ". " + titles[epid-1]);
        title.setTypeface(clarendon);

        TextView subtitle = (TextView)findViewById(R.id.subtitle);
        //subtitle.setText(r.getString(R.string.ep1_subtitle));
        subtitle.setTypeface(clarendon);

        final ImageView placeholder = (ImageView)findViewById(R.id.imageView);

        wv = (WebView)findViewById(R.id.webView);
        if (savedInstanceState != null) {
            wv.restoreState(savedInstanceState);
        } else {
            wv.loadUrl("file:///android_asset/www/"+epid+".html");
        }
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wv.setVisibility(View.VISIBLE);
                placeholder.setVisibility(View.GONE);
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

        Button next = (Button)findViewById(R.id.nextButton);
        next.setText("Episode "+(epid+1)+"\n" + titles[epid]);
        next.setTypeface(clarendon);
        next.setLineSpacing(0, 1.3f);
    }

    public void nextEpisode(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("epid", epid+1);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
