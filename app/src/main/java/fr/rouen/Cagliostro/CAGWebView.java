package fr.rouen.Cagliostro;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class CAGWebView extends WebView {


    public CAGWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*@Override
    public void invalidate() {
        super.invalidate();

        if (getHeight() > 0) {
            ((EpisodeActivity)getContext()).placePins(getHeight());
            ((EpisodeActivity)getContext()).updatePins();
        }
    }*/
}
