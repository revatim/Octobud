package com.project.design.oswald.activities;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.project.design.oswald.R;
import com.project.design.oswald.sql.DatabaseHelper;
import com.project.design.oswald.sql.OptionsHelper;

public class AlphabetActivity extends AppCompatActivity {
   // OptionsHelper optionsHelper;
    DatabaseHelper databaseHelper;
    AppCompatActivity activity = AlphabetActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alphabet);
        int count=1;
        final String email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(activity);
        String level = databaseHelper.getAlphabetsD(email);
        Log.i("hi",level);
      //  optionsHelper = new OptionsHelper(activity);
       // String s  = optionsHelper.getAlphabet("EASY", "B");
        final VideoView videoView = (VideoView) findViewById(R.id.video);
        if (count == 1)
        {
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.easyb);
            videoView.start();
            //  video.start();
        }
    }


}