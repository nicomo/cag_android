package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceAdapter extends BaseAdapter {

    private Context context;
    private JSONArray places;
    private Typeface georgia;
    SharedPreferences prefs;

    public PlaceAdapter(Context context, JSONArray places) {
        this.context = context;
        this.places = places;
        this.georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final double d = context.getResources().getDisplayMetrics().density;

        View v = new View(context);

        try {

            JSONObject place = places.getJSONObject(position);

            ImageView placebtn = new ImageView(context);
            if (position < 7) {
                placebtn.setImageResource(R.drawable.place_abbey);
            } else {
                placebtn.setImageResource(R.drawable.place_other);
            }
            MapView.LayoutParams lp = new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT);
            lp.height = (int)(40*d);
            lp.width = (int)(40*d);
            lp.x = place.getDouble("x");
            lp.y = place.getDouble("y");

            placebtn.setLayoutParams(lp);

            return placebtn;

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return v;
    }

    @Override
    public int getCount() {
        return places.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

