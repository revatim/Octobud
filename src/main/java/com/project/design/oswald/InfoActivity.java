package com.project.design.oswald;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.design.oswald.activities.ChooseActivity;
import com.project.design.oswald.activities.NameActivity;
import com.project.design.oswald.activities.SelectDifficulty;
import com.project.design.oswald.activities.WelActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info);
        TextView tx = (TextView)findViewById(R.id.textView3);
        ImageButton btn = (ImageButton)findViewById(R.id.next3);
        ImageButton btn1 = (ImageButton)findViewById(R.id.back3);
        final String email = getIntent().getStringExtra("EMAIL");
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        tx.setTypeface(myFont);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //user.setProduct_id(product_id.getText().toString().trim());
                Intent i = new Intent(InfoActivity.this, SelectDifficulty.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(InfoActivity.this, WelActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
    }
}
