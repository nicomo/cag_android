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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfessionsAdapter extends BaseAdapter {

    private Context context;
    private JSONArray confessions;
    private Typeface georgia;
    SharedPreferences prefs;
    private int cid;

    public ConfessionsAdapter(Context context, JSONArray confessions, int cid) {
        this.context = context;
        this.georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.confessions = confessions;
        this.cid = cid;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.confessions_card, null);

        int q1id = position*2;
        int q2id = (position*2)+1;

        try {
            TextView q1tv = (TextView) v.findViewById(R.id.q1);
            q1tv.setTypeface(georgia, Typeface.BOLD);
            q1tv.setText(confessions.getJSONObject(q1id).getString("question"));

            TextView r1tv = (TextView) v.findViewById(R.id.r1);
            r1tv.setTypeface(georgia, Typeface.ITALIC);
            r1tv.setText(confessions.getJSONObject(q1id).getJSONArray("reponses").getString(cid));

            TextView q2tv = (TextView) v.findViewById(R.id.q2);
            q2tv.setTypeface(georgia, Typeface.BOLD);
            q2tv.setText(confessions.getJSONObject(q2id).getString("question"));

            TextView r2tv = (TextView) v.findViewById(R.id.r2);
            r2tv.setTypeface(georgia, Typeface.ITALIC);
            r2tv.setText(confessions.getJSONObject(q2id).getJSONArray("reponses").getString(cid));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public int getCount() {
        return confessions.length() / 2;
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
