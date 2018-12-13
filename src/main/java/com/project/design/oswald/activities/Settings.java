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
import com.project.design.oswald.model.UserD;
import com.project.design.oswald.sql.DatabaseHelper;
import com.project.design.oswald.sql.DifficultyHelper;

public class Settings extends AppCompatActivity {
    private UserD user;
    private DatabaseHelper df;
    private AppCompatActivity activity = Settings.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        TextView tx = findViewById(R.id.choose);
        TextView tx1 = findViewById(R.id.diffiText);
        df = new DatabaseHelper(activity);
        final String email = getIntent().getStringExtra("EMAIL");
        final String SELEC = getIntent().getStringExtra("SELEC");
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        //set current level in text view
        String current_level = null;
      //  String current_level = df.getAlphabetsD(email) ;
        if(SELEC == "alphabets")
        {
            current_level = df.getAlphabetsD(email);
        }
        else if (SELEC == "words")
        {
            current_level = df.getWordsD(email);
        }
        else if (SELEC == "sentences")
        {
            current_level = df.getSentencessD(email);
        }
        else if (SELEC == "poems")
        {
            current_level = df.getPoemsD(email);
        }
        else if (SELEC == "colors")
        {
            current_level = df.getColorsD(email);
        }
        else if (SELEC == "manners")
        {
            current_level = df.getMannersD(email);
        }
        else if (SELEC == "numbers")
        {
            current_level = df.getNumbersD(email);
        }
        else
        {
            current_level = df.getSpD(email);
        }
        current_level = "  " + current_level;
        tx1.append(current_level);


        tx.setTypeface(myFont);
        tx1.setTypeface(myFont);

        ImageButton easy = findViewById(R.id.easy);
        ImageButton medium = findViewById(R.id.medium);
        ImageButton hard = findViewById(R.id.hard);
        ImageButton goBack = findViewById(R.id.backSelectDiff);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent easyn = new Intent(Settings.this,SelectDifficultyPage2.class);
                String level = "EASY";
                if(SELEC == "alphabets")
                {
                   // user.setAlphabets("EASY");
                    df.setAlphabetsD(email, level);
                }
                else if (SELEC == "words")
                {
                    //user.setWords("EASY");
                    df.setWordsD(email, level);
                }
                else if (SELEC == "sentences")
                {
                   // user.setSentences("EASY");
                    df.setSentencesD(email, level);
                }
                else if (SELEC == "poems")
                {
                    //user.setPoems("EASY");
                    df.setPoemsD(email, level);
                }
                else if (SELEC == "colors")
                {
                    //user.setColors("EASY");
                    df.setColorsD(email, level);
                }
                else if (SELEC == "manners")
                {
                    //user.setManners("EASY");
                    df.setMannersD(email, level);
                }
                else if (SELEC == "numbers")
                {
                    //user.setNumbers("EASY");
                    df.setNumbersD(email, level);
                }
                else
                {
                    //user.setSp("EASY");
                    df.setSpD(email, level);
                }

                easyn.putExtra("EMAIL",email);
                startActivity(easyn);

            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mediumn = new Intent(Settings.this,SelectDifficultyPage2.class);

                String level = "MEDIUM";
                if(SELEC == "alphabets")
                {
                    // user.setAlphabets("EASY");
                    df.setAlphabetsD(email, level);
                }
                else if (SELEC == "words")
                {
                    //user.setWords("EASY");
                    df.setWordsD(email, level);
                }
                else if (SELEC == "sentences")
                {
                    // user.setSentences("EASY");
                    df.setSentencesD(email, level);
                }
                else if (SELEC == "poems")
                {
                    //user.setPoems("EASY");
                    df.setPoemsD(email, level);
                }
                else if (SELEC == "colors")
                {
                    //user.setColors("EASY");
                    df.setColorsD(email, level);
                }
                else if (SELEC == "manners")
                {
                    //user.setManners("EASY");
                    df.setMannersD(email, level);
                }
                else if (SELEC == "numbers")
                {
                    //user.setNumbers("EASY");
                    df.setNumbersD(email, level);
                }
                else
                {
                    //user.setSp("EASY");
                    df.setSpD(email, level);
                }

                mediumn.putExtra("EMAIL",email);
                startActivity(mediumn);

            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hardn = new Intent(Settings.this,SelectDifficultyPage2.class);

                String level = "HARD";
                if(SELEC == "alphabets")
                {
                    // user.setAlphabets("EASY");
                    df.setAlphabetsD(email, level);
                }
                else if (SELEC == "words")
                {
                    //user.setWords("EASY");
                    df.setWordsD(email, level);
                }
                else if (SELEC == "sentences")
                {
                    // user.setSentences("EASY");
                    df.setSentencesD(email, level);
                }
                else if (SELEC == "poems")
                {
                    //user.setPoems("EASY");
                    df.setPoemsD(email, level);
                }
                else if (SELEC == "colors")
                {
                    //user.setColors("EASY");
                    df.setColorsD(email, level);
                }
                else if (SELEC == "manners")
                {
                    //user.setManners("EASY");
                    df.setMannersD(email, level);
                }
                else if (SELEC == "numbers")
                {
                    //user.setNumbers("EASY");
                    df.setNumbersD(email, level);
                }
                else
                {
                    //user.setSp("EASY");
                    df.setSpD(email, level);
                }

                hardn.putExtra("EMAIL",email);
                startActivity(hardn);

            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Settings.this, SelectDifficultyPage2.class);
                back.putExtra("EMAIL",email);
                startActivity(back);
            }
        });

    }
}
