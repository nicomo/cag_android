package fr.rouen.Cagliostro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.content.res.Configuration;

public class MainActivity extends Activity {

    int epid;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        epid = intent.getIntExtra("epid", 1);

        class JSI {
            @JavascriptInterface
            public void nextEpisode(){
                nextEpisodeDo();
            }
        }

        System.out.println("######" + R.raw.bg);

        wv = new WebView(this);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.addJavascriptInterface(new JSI(), "AndroidFunction");

        if (savedInstanceState != null) {
            wv.restoreState(savedInstanceState);
        } else {
            wv.loadUrl("file:///android_asset/www/"+epid+".html");
        }

        //setContentView(wv.getLayout());

        /*wv = new WebView(this);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new JSI(), "AndroidFunction");
        wv.loadUrl("file:///android_asset/www/"+epid+".html");*/

        setContentView(wv);

        /*VideoView videoHolder = new VideoView(this);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.bg); //do not add any extension
        videoHolder.setVideoURI(video);
        setContentView(videoHolder);
        videoHolder.start();

        setContentView(videoHolder);*/
    }

    public void nextEpisodeDo() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("epid", epid+1);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
