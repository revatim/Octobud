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

import com.project.design.oswald.R;

public class ChooseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose);
        TextView tx = findViewById(R.id.sel);
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        tx.setTypeface(myFont);
        final String email = getIntent().getStringExtra("EMAIL");
        ImageButton alphabets = findViewById(R.id.alphabets);
        ImageButton colors = findViewById(R.id.colors);
        ImageButton numbers = findViewById(R.id.numbers);
        ImageButton sentences = findViewById(R.id.sentences);
        ImageButton words = findViewById(R.id.words);
        ImageButton poems = findViewById(R.id.poems);
        ImageButton manners = findViewById(R.id.manners);
        ImageButton specialedition = findViewById(R.id.specialedition);
        ImageButton settings = findViewById(R.id.setting);
    settings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i  = new Intent(ChooseActivity.this,SelectDifficulty.class);
            i.putExtra("EMAIL",email);
            startActivity(i);
        }
    }
    );
        alphabets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(ChooseActivity.this,AlphabetActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);

            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ChooseActivity.this,AlphabetActivity.class));
                Intent i  = new Intent(ChooseActivity.this,ColorsActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);

            }
        });
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ChooseActivity.this,AlphabetActivity.class));
                Intent i  = new Intent(ChooseActivity.this,NumbersActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);

            }
        });
        manners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this,MannersActivity.class));
                Intent i  = new Intent(ChooseActivity.this,MannersActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);

            }
        });
        sentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ChooseActivity.this,AlphabetActivity.class));
                Intent i  = new Intent(ChooseActivity.this,SentenceActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
        poems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ChooseActivity.this,AlphabetActivity.class));
                Intent i  = new Intent(ChooseActivity.this,PoemsActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ChooseActivity.this,AlphabetActivity.class));

                Intent i  = new Intent(ChooseActivity.this,WordsActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
        specialedition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ChooseActivity.this,AlphabetActivity.class));
                Intent i  = new Intent(ChooseActivity.this,SpecialActivity.class);
                i.putExtra("EMAIL",email);
                startActivity(i);
            }
        });
    }
}
