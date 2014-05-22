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

        ImageView avatar = (ImageView) v.findViewById(R.id.avatar);
        avatar.setImageResource(this.getResources().getIdentifier("pin_" + cid, "drawable", getActivity().getPackageName()));;

        TextView rtv = (TextView) v.findViewById(R.id.ref);
        rtv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.ITALIC);
        try {
            rtv.setText(messages.getJSONObject(position).getString("msg_ref"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView mtv = (TextView) v.findViewById(R.id.mess);
        mtv.setTypeface(((CharacterActivity)getActivity()).georgia, Typeface.NORMAL);
        try {
            mtv.setText(messages.getJSONObject(position).getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }
}
