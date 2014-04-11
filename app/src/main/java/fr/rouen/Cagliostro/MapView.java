package fr.rouen.Cagliostro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

public class MapView extends AdapterView<PlaceAdapter> {

    private PlaceAdapter mAdapter;
    private Context context;

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public PlaceAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(PlaceAdapter placeAdapter) {
        mAdapter = placeAdapter;
        removeAllViewsInLayout();
        requestLayout();
    }

    @Override
    public View getSelectedView() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        final double d = context.getResources().getDisplayMetrics().density;

        if (mAdapter == null) {
            return;
        }

        if (getChildCount() == 0) {
            for (int position = 0; position < mAdapter.getCount(); position++) {
                View child = mAdapter.getView(position, null, this);

                LayoutParams params = (LayoutParams)child.getLayoutParams();
                if (params == null) {
                    params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                }
                addViewInLayout(child, -1, params, true);

                child.layout(
                        (int)(params.x*this.getWidth()) - params.width/2,
                        (int)(params.y*this.getHeight()) - params.height/2,
                        (int)(params.x*this.getWidth()) + params.width/2,
                        (int)(params.y*this.getHeight()) + params.height/2
                );
            }
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        double x;
        double y;

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
    }
}
