package fr.rouen.Cagliostro;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class PhotoPage extends Fragment {

    public int position;
    Context context;
    int plid;
    String prefix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.photo_card, container, false);

        ImageView img = (ImageView) v.findViewById(R.id.image);
        img.setImageResource(context.getResources().getIdentifier(prefix + "_" + plid + "_image_" + position, "drawable", context.getPackageName()));

        return v;
    }
}
