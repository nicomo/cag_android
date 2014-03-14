package fr.rouen.Cagliostro;

import android.content.Context;
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

public class CharacterCardAdapter extends BaseAdapter {

    private Context context;
    private JSONArray characters;

    public CharacterCardAdapter(Context context, JSONArray characters) {
        this.context = context;
        this.characters = characters;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v;

        if (convertView == null) {
            v = new View(context);
            v = inflater.inflate(R.layout.character_card, null);

            try {
                JSONObject character = characters.getJSONObject(position);

                TextView name = (TextView) v.findViewById(R.id.name);
                name.setText(character.getString("name"));

                ImageView avatar = (ImageView) v.findViewById(R.id.avatar);
                int iden = context.getResources().getIdentifier("pin_" + (position+1), "drawable", context.getPackageName());
                avatar.setImageResource(iden);
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }

        } else {
            v = (View) convertView;
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
