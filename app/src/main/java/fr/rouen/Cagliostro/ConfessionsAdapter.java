package fr.rouen.Cagliostro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;

public class ConfessionsAdapter extends FragmentPagerAdapter {

    JSONArray confessions;
    int cid;

    public ConfessionsAdapter(FragmentManager fm, JSONArray confessions, int cid) {
        super(fm);
        this.confessions = confessions;
        this.cid = cid;
    }

    @Override
    public Fragment getItem(int position) {
        ConfessionPage f = new ConfessionPage();
        f.position = position;
        f.cid = this.cid;
        f.confessions = this.confessions;
        return f;
    }

    @Override
    public int getCount() {
        return this.confessions.length() / 2;
    }
}

