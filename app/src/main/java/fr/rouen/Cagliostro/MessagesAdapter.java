package fr.rouen.Cagliostro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;

public class MessagesAdapter extends FragmentPagerAdapter {

    JSONArray messages;
    int cid;

    public MessagesAdapter(FragmentManager fm, JSONArray messages, int cid) {
        super(fm);
        this.messages = messages;
        this.cid = cid;
    }

    @Override
    public Fragment getItem(int position) {
        MessagePage f = new MessagePage();
        f.position = position;
        f.cid = this.cid;
        try {
            f.messages = this.messages.getJSONArray(cid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public int getCount() {
        try {
            return this.messages.getJSONArray(cid).length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

