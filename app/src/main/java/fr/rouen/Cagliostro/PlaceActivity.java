package fr.rouen.Cagliostro;

import android.app.Activity;
import android.os.Bundle;

public class PlaceActivity extends Activity {
    int plid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
