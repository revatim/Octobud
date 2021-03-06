package com.project.design.oswald.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.design.oswald.R;

public class WelcomeBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_back);
        TextView tx= (TextView)findViewById(R.id.welcome_back);
        final String email = getIntent().getStringExtra("EMAIL");

        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica Bold.otf");
        tx.setTypeface(myFont);
        ImageView img = (ImageView) findViewById(R.id.background_wel);
        img.setOnClickListener(new View.OnClickListener() {
            private int clickCount;

            @Override
            public void onClick(View v) {
                if (++clickCount == 2) {
                    Intent intent = new Intent(WelcomeBackActivity.this, ChooseActivity.class);
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                }
            }
        });
    }
}
