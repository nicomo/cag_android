package fr.rouen.Cagliostro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;

public class PhotosAdapter extends FragmentPagerAdapter {

    int num;
    int plid;
    Context context;

    public PhotosAdapter(FragmentManager fm, int num, Context context, int plid) {
        super(fm);
        this.num = num;
        this.context = context;
        this.plid = plid;
    }

    @Override
    public Fragment getItem(int position) {
        PhotoPage f = new PhotoPage();
        f.position = position;
        f.context = this.context;
        f.plid = this.plid;
        return f;
    }

    @Override
    public int getCount() {
        return this.num;
    }
}

