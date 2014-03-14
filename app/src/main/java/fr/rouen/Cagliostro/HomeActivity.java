package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import org.json.JSONArray;

public class HomeActivity extends Activity {

    JSONArray characters;
    JSONArray episodes;
    boolean charExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CAGApp appState = ((CAGApp)getApplicationContext());
        characters = appState.getCharacters();
        episodes = appState.getEpisodes();

        setContentView(R.layout.home);

        Typeface clarendon = Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf");
        Typeface georgia = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");

        TextView journal = (TextView)findViewById(R.id.journal);
        journal.setTypeface(clarendon);

        TextView author = (TextView)findViewById(R.id.author);
        author.setTypeface(georgia);

        TextView title = (TextView)findViewById(R.id.title);
        title.setTypeface(clarendon);

        TextView characterstitle = (TextView)findViewById(R.id.characterstitle);
        characterstitle.setTypeface(clarendon);

        TextView placestitle = (TextView)findViewById(R.id.placestitle);
        placestitle.setTypeface(clarendon);

        StaggeredGridView episodesgrid = (StaggeredGridView) findViewById(R.id.episodesgrid);

        episodesgrid.setAdapter(new EpisodeCardAdapter(this, episodes));

        GridView charactersgrid = (GridView)findViewById(R.id.charactersgrid);

        charactersgrid.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    return true;
                }
                return false;
            }

        });

        final Context context = this;

        charactersgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(context, CharacterActivity.class);
                intent.putExtra("cid", position);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });

        charactersgrid.setAdapter(new CharacterCardAdapter(this, characters));
    }

    public void toggleEpisodes(View view) {


    }

    public void toggleCharacters(View view) {

        final GridView v = (GridView)findViewById(R.id.charactersgrid);
        final double d = v.getContext().getResources().getDisplayMetrics().density;

        if (charExpanded == false) {
            final int initsize = 320;
            final int targetsize = 1120;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? (int) (targetsize * d)
                            : (int) ((initsize * d) + ((targetsize - initsize) * d * interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    TextView t = (TextView) findViewById(R.id.char_expander_label);
                    t.setText("CACHER LES PERSONNAGES");

                    ImageView i = (ImageView) findViewById(R.id.char_expander_icon);
                    i.setImageResource(R.drawable.expand_up);

                    charExpanded = true;
                }
            });

            // 1dp/ms
            a.setDuration((int) (500 / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
        }
        else
        {
            final int initsize = 1120;
            final int targetsize = 320;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? (int) (targetsize * d)
                            : (int) ((initsize * d) + ((targetsize - initsize) * d * interpolatedTime));
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            a.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    TextView t = (TextView) findViewById(R.id.char_expander_label);
                    t.setText("TOUS LES PERSONNAGES");

                    ImageView i = (ImageView) findViewById(R.id.char_expander_icon);
                    i.setImageResource(R.drawable.expand_down);

                    charExpanded = false;
                }
            });

            // 1dp/ms
            a.setDuration((int) (500 / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
        }
    }

}
