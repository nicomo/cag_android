package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by kivutar on 01/03/2014.
 */
public class CAGWebView extends WebView {


    public CAGWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if (getHeight() > 0) {
            ((MainActivity)getContext()).placePins(getHeight());
        }
    }
}
