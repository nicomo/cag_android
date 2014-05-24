package fr.rouen.Cagliostro;

import android.app.*;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PlaceActivity extends FragmentActivity {

    int plid;
    JSONArray places;
    PlaceAdapter pla;
    PhotosAdapter pha;
    Typeface georgia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);

        Intent intent = getIntent();
        plid = intent.getIntExtra("plid", 0);

        CAGApp appState = ((CAGApp)getApplicationContext());
        places = appState.getPlaces();

        georgia = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");

        try {
            ViewPager photopager = (ViewPager)findViewById(R.id.photospager);
            pha = new PhotosAdapter(super.getSupportFragmentManager(), places.getJSONObject(plid).getInt("numphotos"), this, plid, "place");
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
            TextView name = (TextView)findViewById(R.id.name);
            name.setText(places.getJSONObject(plid).getString("name"));
            name.setTypeface(georgia, Typeface.BOLD);

            TextView intro = (TextView)findViewById(R.id.intro);
            intro.setText(places.getJSONObject(plid).getString("intro"));
            intro.setTypeface(georgia);

            TextView historique = (TextView)findViewById(R.id.historique);
            historique.setText(places.getJSONObject(plid).getString("historique"));
            historique.setTypeface(georgia);

            TextView itin1 = (TextView)findViewById(R.id.itin1);
            itin1.setText(places.getJSONObject(plid).getJSONArray("itineraire").getString(0));
            itin1.setTypeface(georgia);

            TextView itin2 = (TextView)findViewById(R.id.itin2);
            itin2.setText(places.getJSONObject(plid).getJSONArray("itineraire").getString(1));
            itin2.setTypeface(georgia);

            TextView itin3 = (TextView)findViewById(R.id.itin3);
            itin3.setText(places.getJSONObject(plid).getJSONArray("itineraire").getString(2));
            itin3.setTypeface(georgia);

            TextView savoir = (TextView)findViewById(R.id.savoir);
            savoir.setText(places.getJSONObject(plid).getString("savoir"));
            savoir.setTypeface(georgia);

            TextView events = (TextView)findViewById(R.id.events);
            events.setText(places.getJSONObject(plid).getString("events"));
            events.setTypeface(georgia);

            LinearLayout celebsl = (LinearLayout)findViewById(R.id.celebs);
            JSONArray jcelebs = places.getJSONObject(plid).getJSONArray("celebs");
            for (int i = 0; i < jcelebs.length(); i++)
            {
                String cs = places.getJSONObject(plid).getJSONArray("celebs").getString(i);
                TextView ctv = new TextView(this);
                ctv.setText(cs);
                ctv.setTypeface(georgia);
                ctv.setPadding(0, 0, 0, 15);
                celebsl.addView(ctv);
            }

            LinearLayout conversationl = (LinearLayout)findViewById(R.id.conversation);
            JSONArray jconversation = places.getJSONObject(plid).getJSONArray("conversation");
            for (int i = 0; i < jconversation.length(); i++)
            {
                String cs = places.getJSONObject(plid).getJSONArray("conversation").getString(i);
                TextView ctv = new TextView(this);
                ctv.setText(cs);
                ctv.setTypeface(georgia);
                ctv.setPadding(0, 0, 0, 15);
                conversationl.addView(ctv);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray epplaces = new JSONArray();
        epplaces.put(plid);
        MapView map = (MapView)findViewById(R.id.map);
        pla = new PlaceAdapter(this, places, epplaces);
        map.setAdapter(pla);

        ImageView bottomimage0 = (ImageView)findViewById(R.id.bottomimage0);
        bottomimage0.setBackgroundResource(getResources().getIdentifier("place_" + plid + "_bottomimage_0", "drawable", getPackageName()));

        ImageView bottomimage1 = (ImageView)findViewById(R.id.bottomimage1);
        bottomimage1.setBackgroundResource(getResources().getIdentifier("place_" + plid + "_bottomimage_1", "drawable", getPackageName()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
