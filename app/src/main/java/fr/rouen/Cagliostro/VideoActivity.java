package fr.rouen.Cagliostro;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

    private VideoView mVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        mVV = (VideoView)findViewById(R.id.videoview);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + getResources().getIdentifier("bonus_1", "raw", getPackageName()));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVV);
        mVV.setMediaController(mediaController);

        mVV.setVideoURI(uri);

        mVV.start();
    }
}
