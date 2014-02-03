package fr.rouen.Cagliostro;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.View;
import ir.noghteh.JustifiedTextView;
import android.util.TypedValue;
import android.graphics.Paint.Align;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        float d = getResources().getDisplayMetrics().density;

        String[] episode1Array = getResources().getStringArray(R.array.ep1_paragraphs);

        setContentView(R.layout.episode);

        TableLayout table = (TableLayout)findViewById(R.id.tableLayout);

        TextView title = (TextView)findViewById(R.id.title);
        title.setText("1. " + getResources().getString(R.string.ep1_title));
        title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf"));

        TextView subtitle = (TextView)findViewById(R.id.subtitle);
        subtitle.setText(getResources().getString(R.string.ep1_subtitle));
        subtitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf"));

        for (String s : episode1Array) {

            TableRow tr = new TableRow(this);

            JustifiedTextView tv = new JustifiedTextView(this);
            tv.setText(s);
            tv.setTextColor(Color.parseColor("#151313"));
            tv.setBackground(getResources().getDrawable(R.drawable.paragraph));
            tv.setPadding(0, 0, (int)(40 * d), 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tv.setAlignment(Align.LEFT);
            tv.setLineSpace(15);
            tv.setTypeFace(Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf"));
            TableRow.LayoutParams tvlp = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f);
            tvlp.setMargins((int)(100 * d), 0, (int)(180 * d), 0);
            tv.setLayoutParams(tvlp);
            tr.addView(tv);

            table.addView(tr);
        }

        Button next = (Button)findViewById(R.id.nextButton);
        next.setText("Episode 2\n" + getResources().getString(R.string.ep1_nexttitle));
        next.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SuperClarendon_7.otf"));
        next.setLineSpacing(0, 1.3f);
    }

    public void nextEpisode(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
