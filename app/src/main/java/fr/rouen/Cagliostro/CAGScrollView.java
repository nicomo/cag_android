package fr.rouen.Cagliostro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CAGScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener = null;
    public CAGScrollView(Context context) {
        super(context);
    }

    public CAGScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CAGScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
