package com.project.design.oswald.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.project.design.oswald.R;
import com.project.design.oswald.sql.DatabaseHelper;

public class OrientationActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    //   private User user;
    private final AppCompatActivity activity = OrientationActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_orientation);
        TextView tx= (TextView)findViewById(R.id.screenorien);
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        tx.setTypeface(myFont);
        final String email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(activity);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
                String ck =databaseHelper.getCheck(email);
                String k = ck;
                Intent mInHome = new Intent(OrientationActivity.this, WelcomeActivity.class);
                mInHome.putExtra("EMAIL", email);
                OrientationActivity.this.startActivity(mInHome);
                OrientationActivity.this.finish();
              //  if(ck != null)
              //  {
                //    Intent alreadyDone = new Intent(OrientationActivity.this,WelcomeBackActivity.class);
                  //  alreadyDone.putExtra("EMAIL",email);
                    //startActivity(alreadyDone);
                    //OrientationActivity.this.finish();
               // }
                //else {
                 //   Intent mInHome = new Intent(OrientationActivity.this, WelcomeActivity.class);
                   // mInHome.putExtra("EMAIL", email);
                   // OrientationActivity.this.startActivity(mInHome);
                   // OrientationActivity.this.finish();
                //}
            }
        }, 3500);

    }
}
