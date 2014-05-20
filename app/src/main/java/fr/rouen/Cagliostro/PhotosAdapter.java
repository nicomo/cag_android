package fr.rouen.Cagliostro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;

public class PhotosAdapter extends FragmentPagerAdapter {

    int num;
    int plid;

    public PhotosAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        PhotoPage f = new PhotoPage();
        f.position = position;
        return f;
    }

    @Override
    public int getCount() {
        return this.num;
    }
}

