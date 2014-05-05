package fr.rouen.Cagliostro;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MessagePage extends Fragment {

    public int position;
    public int cid;
    public JSONArray messages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.messages_card, container, false);

        int m1 = position*3;
        int m2 = (position*3)+1;
        int m3 = (position*3)+2;

        ImageView m1iv = (ImageView) v.findViewById(R.id.avatar1);
        ImageView m2iv = (ImageView) v.findViewById(R.id.avatar2);
        ImageView m3iv = (ImageView) v.findViewById(R.id.avatar3);

        m1iv.setImageResource(this.getResources().getIdentifier("pin_" + cid, "drawable", getActivity().getPackageName()));;
        m2iv.setImageResource(this.getResources().getIdentifier("pin_" + cid, "drawable", getActivity().getPackageName()));;
        m3iv.setImageResource(this.getResources().getIdentifier("pin_" + cid, "drawable", getActivity().getPackageName()));;

        TextView m1tv = (TextView) v.findViewById(R.id.m1);
        m1tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.ITALIC);
        try {
            m1tv.setText(messages.getJSONObject(m1).getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView m2tv = (TextView) v.findViewById(R.id.m2);
        m2tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.ITALIC);
        try {
            m2tv.setText(messages.getJSONObject(m2).getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView m3tv = (TextView) v.findViewById(R.id.m3);
        m3tv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.ITALIC);
        try {
            m3tv.setText(messages.getJSONObject(m3).getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }
}
