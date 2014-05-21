package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceActivity extends FragmentActivity {

    int plid;
    JSONArray places;
    PlaceAdapter pla;
    PhotosAdapter pha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);

        Intent intent = getIntent();
        plid = intent.getIntExtra("plid", 0);

        CAGApp appState = ((CAGApp)getApplicationContext());
        places = appState.getPlaces();

        try {
            ViewPager photopager = (ViewPager)findViewById(R.id.photospager);
            pha = new PhotosAdapter(super.getSupportFragmentManager(), places.getJSONObject(plid).getInt("numphotos"), this, plid);
            photopager.setAdapter(pha);

            CirclePageIndicator ind = (CirclePageIndicator)findViewById(R.id.photospagerindicator);
            ind.setViewPager(photopager);
            ind.setFillColor(Color.parseColor("#151313"));
            ind.setPageColor(Color.parseColor("#beb2b0"));
            ind.setStrokeWidth(0);
            ind.setRadius(8);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            getActionBar().setTitle(places.getJSONObject(plid).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray epplaces = new JSONArray();
        epplaces.put(plid);
        MapView map = (MapView)findViewById(R.id.map);
        pla = new PlaceAdapter(this, places, epplaces);
        map.setAdapter(pla);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
