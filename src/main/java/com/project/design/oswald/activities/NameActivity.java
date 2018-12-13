package com.project.design.oswald.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.UserManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.project.design.oswald.model.User;

import com.project.design.oswald.R;
import com.project.design.oswald.sql.DatabaseHelper;

import java.util.jar.Attributes;


public class NameActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
 //   private User user;
    private final AppCompatActivity activity = NameActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_name);
        final ImageButton btn = (ImageButton)findViewById(R.id.next1);
        final TextView tx= (TextView)findViewById(R.id.hello);
        final TextView tx1= (TextView)findViewById(R.id.name);
        final EditText tx2= (EditText) findViewById(R.id.nameis);
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        Typeface myFont1 = Typeface.createFromAsset(getAssets(), "fonts/Generica Bold.otf");

        // retrieve the parents name here
        final String email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(activity);
        String name_set = databaseHelper.getParentName(email);

        if(databaseHelper.getChildName(email)!=null)
        {
            tx2.setText(databaseHelper.getChildName(email));

        }
        tx.append(" " + name_set);
        tx.setTypeface(myFont1);
        tx1.setTypeface(myFont);


      //  user = new User();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(NameActivity.this, WelActivity.class);
                String child_name = tx2.getText().toString().trim();
//                if(child_name.length()<3){
//                    tx2.setError("Enter valid name ");
//                }
                if(databaseHelper.setChildName(email, child_name)){
                    next.putExtra("EMAIL", email);
                    if((databaseHelper.getChildName(email).length()>3)&&(databaseHelper.getProduct(email)!=null)){
                            Toast.makeText(NameActivity.this, "done", Toast.LENGTH_SHORT).show();
                            if (databaseHelper.setDone(email)){
                                Toast.makeText(NameActivity.this, "set done is set", Toast.LENGTH_SHORT).show();
                            }
                        startActivity(next);
                    }
                   else
                    {
                        Intent same = new Intent(NameActivity.this , NameActivity.class);
                        Toast.makeText(NameActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                        same.putExtra("EMAIL" ,email);
                        startActivity(same);
                    }
                }
                else{
                   tx2.setText("unable to set");
                   Intent same = new Intent(NameActivity.this , NameActivity.class);
                    Toast.makeText(NameActivity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                   same.putExtra("EMAIL" ,email);
                   startActivity(same);
                }

            }
        });

    }
}
