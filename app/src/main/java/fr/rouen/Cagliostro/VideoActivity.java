package fr.rouen.Cagliostro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

    private VideoView mVV;
    int vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        Intent intent = getIntent();
        vid = intent.getIntExtra("vid", 1);

        mVV = (VideoView)findViewById(R.id.videoview);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + getResources().getIdentifier("bonus_" + vid, "raw", getPackageName()));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVV);
        mVV.setMediaController(mediaController);

        mVV.setVideoURI(uri);

        mVV.start();
    }
}
