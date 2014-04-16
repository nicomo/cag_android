package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FooterCharacterAdapter extends BaseAdapter {

    private Context context;
    private JSONArray characters;
    private JSONArray epchars;
    private Typeface georgia;
    SharedPreferences prefs;
    boolean home;

    public FooterCharacterAdapter(Context context, JSONArray characters, JSONArray epchars) {
        this.context = context;
        this.characters = characters;
        this.georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.home = false;
        this.epchars = epchars;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.footer_character_card, null);

        try {
            JSONObject epchar = epchars.getJSONObject(position);
            ImageView avatar = (ImageView) v.findViewById(R.id.avatar);
            int iden = context.getResources().getIdentifier("pin_" + epchar.getInt("cid"), "drawable", context.getPackageName());
            avatar.setImageResource(iden);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return v;
    }

    @Override
    public int getCount() {
        return epchars.length();
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
