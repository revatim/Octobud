package com.project.design.oswald.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.design.oswald.R;

public class SpecialActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_special);
        TextView tx = (TextView)findViewById(R.id.special);
        final String email = getIntent().getStringExtra("EMAIL");

        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica Bold.otf");
        tx.setTypeface(myFont);
        ImageView img = (ImageView) findViewById(R.id.background_wel);
    }
}


