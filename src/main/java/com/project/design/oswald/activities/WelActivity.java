package com.project.design.oswald.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.design.oswald.InfoActivity;
import com.project.design.oswald.R;
import com.project.design.oswald.sql.DatabaseHelper;

public class WelActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    //   private User user;
    private final AppCompatActivity activity = WelActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wel);
        TextView tx = (TextView)findViewById(R.id.textView);
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");

        ImageButton btn = (ImageButton)findViewById(R.id.next2);
        ImageButton btn1 = (ImageButton)findViewById(R.id.back2);
        final String email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(activity);
        String name = databaseHelper.getChildName(email);
        String text = "Dear " + name + "'s warden \n" +
                "I’m Octobud. I will help " + name + "\n" +
                "develop cognitive skills in relation to language. \n\n" +
                "This app has been designed to switch off \n" +
                "automatically after use for 20 minutes.\n" +
                "The app cannot be reused unless there’s a gap of\n" +
                "at least 3 hours.";
        tx.setText(text);
        tx.setTypeface(myFont);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //user.setProduct_id(product_id.getText().toString().trim());
                Intent i =new Intent(WelActivity.this, InfoActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelActivity.this, NameActivity.class);
                i.putExtra("EMAIL", email);
                startActivity(i);
            }
        });
    }

}
