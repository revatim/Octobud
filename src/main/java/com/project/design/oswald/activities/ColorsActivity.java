package com.project.design.oswald.activities;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.project.design.oswald.R;
import com.project.design.oswald.sql.DatabaseHelper;
import com.project.design.oswald.sql.OptionsHelper;

public class ColorsActivity extends AppCompatActivity {
    // OptionsHelper optionsHelper;
    DatabaseHelper databaseHelper;
    AppCompatActivity activity = ColorsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int count =1;
        final String email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(activity);
        String level = databaseHelper.getColorsD(email)  ;
        //  optionsHelper = new OptionsHelper(activity);
        // String s  = optionsHelper.getAlphabet("EASY", "B");
        final VideoView videoView = (VideoView) findViewById(R.id.video);
        if (count ==1)
        {
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.easycolors);
            videoView.start();
            //  video.start();
        }
    }


}