package fr.rouen.Cagliostro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

public class HomeActivity extends Activity {

    JSONArray characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CAGApp appState = ((CAGApp)getApplicationContext());
        characters = appState.getCharacters();

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
        characterstitle.setTypeface(georgia);

        TextView placestitle = (TextView)findViewById(R.id.placestitle);
        placestitle.setTypeface(georgia);

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

}
