package fr.rouen.Cagliostro;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EpisodeCardAdapter extends BaseAdapter {

    private Context context;
    private JSONArray episodes;

    static class ViewHolder {
        ImageView photo;
        TextView title;
    }

    public EpisodeCardAdapter(Context context, JSONArray episodes) {
        this.context = context;
        this.episodes = episodes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.episode_card, parent, false);
            vh = new ViewHolder();
            vh.photo = (ImageView) convertView.findViewById(R.id.photo);
            vh.title = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        try {
            JSONObject character = episodes.getJSONObject(position);
            int iden = context.getResources().getIdentifier("homecard_" + (position+1), "drawable", context.getPackageName());
            vh.photo.setImageResource(iden);
            vh.title.setText((position + 1) + ". " + character.getString("title"));
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
