package fr.rouen.Cagliostro;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class ConfessionPage extends Fragment {

    public int position;
    public int cid;
    public JSONArray confessions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.confessions_card, container, false);

        int q1id = position*2;
        int q2id = (position*2)+1;

        try {
            TextView q1tv = (TextView) v.findViewById(R.id.q1);
            q1tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.BOLD);
            q1tv.setText(confessions.getJSONObject(q1id).getString("question"));

            TextView r1tv = (TextView) v.findViewById(R.id.r1);
            r1tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.ITALIC);
            r1tv.setText(confessions.getJSONObject(q1id).getJSONArray("reponses").getString(cid));

            TextView q2tv = (TextView) v.findViewById(R.id.q2);
            q2tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.BOLD);
            q2tv.setText(confessions.getJSONObject(q2id).getString("question"));

            TextView r2tv = (TextView) v.findViewById(R.id.r2);
            r2tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.ITALIC);
            r2tv.setText(confessions.getJSONObject(q2id).getJSONArray("reponses").getString(cid));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }
}
