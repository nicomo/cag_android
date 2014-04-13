package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceAdapter extends BaseAdapter {

    private Context context;
    private JSONArray places;
    private JSONArray epplaces;
    private Typeface georgia;
    SharedPreferences prefs;
    boolean home;

    public PlaceAdapter(Context context, JSONArray places) {
        this.context = context;
        this.places = places;
        this.georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.home = true;
    }

    public PlaceAdapter(Context context, JSONArray places, JSONArray epplaces) {
        this.context = context;
        this.places = places;
        this.georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.epplaces = epplaces;
        this.home = false;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final int plid = position;

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

            placebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, CharacterActivity.class);
                    intent.putExtra("plid", plid);
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            });

            placebtn.setLayoutParams(lp);

            if (this.home) {
                if (!((HomeActivity) context).placepublished(position))
                    placebtn.setVisibility(View.GONE);
            } else {
                placebtn.setVisibility(View.GONE);
                for (int j = 0; j < epplaces.length(); j++) {
                    int jplid = epplaces.getInt(j);
                    System.out.println(jplid);
                    if (jplid == plid)
                        placebtn.setVisibility(View.VISIBLE);
                }
            }

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
        try {
            return places.getJSONObject(position);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

