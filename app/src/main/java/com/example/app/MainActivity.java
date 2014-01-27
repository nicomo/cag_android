package com.example.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ScrollView;
import ir.noghteh.JustifiedTextView;
import android.util.TypedValue;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        float d = getResources().getDisplayMetrics().density;

        String[] episode1Array = getResources().getStringArray(R.array.episode1);

        ScrollView scrv = new ScrollView(this);

        TableLayout table = new TableLayout(this);
        TableLayout.LayoutParams tlp = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT);
        table.setLayoutParams(tlp);
        table.setBackgroundColor(Color.parseColor("#f5efe2"));
        scrv.addView(table);

        for (String s : episode1Array) {

            TableRow tr = new TableRow(this);

            JustifiedTextView tv = new JustifiedTextView(this);
            tv.setText(s);
            tv.setTextColor(Color.parseColor("#151313"));
            TableRow.LayoutParams tvlp = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f);
            tvlp.setMargins((int)(100 * d), (int)(10 * d), (int)(200 * d), (int)(10 * d));
            tv.setLayoutParams(tvlp);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tr.addView(tv);

            table.addView(tr);
        }

        setContentView(scrv);
    }

}
