package fr.rouen.Cagliostro;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

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

        try {
            ZipResourceFile expansionFile = APKExpansionSupport
                    .getAPKExpansionZipFile(context, 11, 0);
            InputStream fileStream = expansionFile.getInputStream(prefix + "_" + plid + "_image_" + position + ".png");
            img.setImageDrawable(Drawable.createFromStream(fileStream, prefix + "_" + plid + "_image_" + position + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return v;
    }
}
