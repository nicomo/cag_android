package fr.rouen.Cagliostro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class CreditsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.credits);

        WebView wv = (WebView)findViewById(R.id.webView);
        wv.setVerticalScrollBarEnabled(false);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/www/credits.html");
    }
}
