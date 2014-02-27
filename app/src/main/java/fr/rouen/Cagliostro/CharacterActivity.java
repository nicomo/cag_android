package fr.rouen.Cagliostro;

import android.app.Activity;
import android.os.Bundle;

public class CharacterActivity extends Activity {
    int cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character);
    }
}
