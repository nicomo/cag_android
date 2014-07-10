package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;

public class EpisodeCardAdapter extends BaseAdapter {

    private Context context;
    private JSONArray episodes;
    private Typeface clarendon;
    SharedPreferences prefs;

    static class ViewHolder {
        ImageView photo;
        TextView title;
    }

    public EpisodeCardAdapter(Context context, JSONArray episodes) {
        this.context = context;
        this.episodes = episodes;
        this.clarendon = Typeface.createFromAsset(context.getAssets(), "fonts/SuperClarendon_7.otf");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.episode_card, parent, false);
            vh = new ViewHolder();
            vh.photo = (DynamicHeightImageView) convertView.findViewById(R.id.photo);
            vh.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        long timestamp = this.prefs.getLong("timestamp", 0);
        Boolean delayedEps = this.prefs.getBoolean("delayedEps", true);

        Date now = new Date();
        final double minElapsed = ( now.getTime() - timestamp ) / (60000.0*60.0*24.0);

        try {
            JSONObject character = episodes.getJSONObject(position);
            int iden;
            if (position > minElapsed && delayedEps) {
                iden = context.getResources().getIdentifier("homecard_" + position + "_", "drawable", context.getPackageName());
            } else {
                iden = context.getResources().getIdentifier("homecard_" + position, "drawable", context.getPackageName());
            }
            vh.photo.setImageResource(iden);
            Drawable drawing = vh.photo.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();
            ((DynamicHeightImageView)vh.photo).setHeightRatio((float)bitmap.getHeight()/(float)bitmap.getWidth());
            vh.title.setText((position + 1) + ". " + character.getString("title"));
            vh.title.setTypeface(this.clarendon);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return episodes.length();
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
