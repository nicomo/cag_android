package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class CharacterCardAdapter extends BaseAdapter {

    private Context context;
    private JSONArray characters;
    private Typeface georgia;
    SharedPreferences prefs;

    public CharacterCardAdapter(Context context, JSONArray characters) {
        this.context = context;
        this.characters = characters;
        this.georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v;

        v = new View(context);
        v = inflater.inflate(R.layout.character_card, null);

        /*long timestamp = this.prefs.getLong("timestamp", 0);
        Boolean delayedEps = this.prefs.getBoolean("delayedEps", true);

        Date now = new Date();
        final double minElapsed = ( now.getTime() - timestamp ) / 60000.0;*/

        try {
            JSONObject character = characters.getJSONObject(position);

            TextView name = (TextView) v.findViewById(R.id.name);
            name.setText(character.getString("name"));
            name.setTypeface(georgia);

            ImageView avatar = (ImageView) v.findViewById(R.id.avatar);
            int iden;
            if (((HomeActivity)context).published(position)) {
                iden = context.getResources().getIdentifier("pin_" + (position+1), "drawable", context.getPackageName());
            } else {
                if (character.getString("gender").equals("Male")) {
                    iden = context.getResources().getIdentifier("pin_male", "drawable", context.getPackageName());
                } else {
                    iden = context.getResources().getIdentifier("pin_female", "drawable", context.getPackageName());
                }
            }
            avatar.setImageResource(iden);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return v;
    }

    @Override
    public int getCount() {
        return 32;
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
