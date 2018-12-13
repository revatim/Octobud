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
import com.project.design.oswald.sql.DatabaseHelper;

public class SelectDifficultyPage2 extends AppCompatActivity {
    private DatabaseHelper databasehelper;
    private AppCompatActivity activity = SelectDifficultyPage2.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_difficulty_page2);
        databasehelper = new DatabaseHelper(activity);

        TextView tx = findViewById(R.id.select);
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        tx.setTypeface(myFont);

        ImageButton alphabets = findViewById(R.id.alphabetsDiff);
        ImageButton colors = findViewById(R.id.colorsDiff);
        ImageButton numbers = findViewById(R.id.numbersDiff);
        ImageButton sentences = findViewById(R.id.sentencesDiff);
        ImageButton words = findViewById(R.id.wordsDiff);
        ImageButton poems = findViewById(R.id.poemsDiff);
        ImageButton manners = findViewById(R.id.mannersDiff);
        ImageButton specialedition = findViewById(R.id.specialeditionDiff);
        ImageButton startbutton = findViewById(R.id.startbutton);
        final String email = getIntent().getStringExtra("EMAIL");
        String name  = databasehelper.getChildName(email);
        String pid = databasehelper.getProduct(email);
        if((name.length()>2)&&(pid.length()>2)) {
            databasehelper.setDone(email);
        }
        alphabets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","alphabets");
                startActivity(I);

            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","colors");
                startActivity(I);

            }
        });
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","numbers");
                startActivity(I);
            }
        });
        manners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","manners");
                startActivity(I);
            }
        });
        sentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","sentences");
                startActivity(I);

            }
        });
        poems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","poems");
                startActivity(I);

            }
        });
        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","words");
                startActivity(I);
            }
        });
        specialedition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(SelectDifficultyPage2.this,Settings.class);
                I.putExtra("EMAIL",email);
                I.putExtra("SELEC","sp");
                startActivity(I);
            }
        });
        startbutton.setOnClickListener(new View.OnClickListener()  {
            private int clickCount;

            @Override
            public void onClick(View v) {
                if (++clickCount == 2) {
                    Intent intent = new Intent(SelectDifficultyPage2.this, ChooseActivity.class);
                    intent.putExtra("EMAIL", email);
                 //   intent.putExtra("SELEC",)
                    startActivity(intent);
                }
            }
        });
//        {
//            @Override
//            public void onClick(View v) {
//                 int clickCount;
//
//                @Override
//                public void onClick(View v) {
//                    if (++clickCount == 2) {
//                        Intent intent = new Intent(SelectDifficultyPage2.this, ChooseActivity.class);
//                        intent.putExtra("EMAIL", email);
//                        intent.putExtra("SELEC","sp");
//                        startActivity(intent);
//                    }
//                };
////                Intent I = new Intent(SelectDifficultyPage2.this,ChooseActivity.class);
////                I.putExtra("EMAIL",email);
////                I.putExtra("SELEC","sp");
////                startActivity(I);
//            }
//        });
    }
}
