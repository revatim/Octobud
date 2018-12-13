package com.project.design.oswald.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.design.oswald.R;
import com.project.design.oswald.helpers.InputValidation;
import com.project.design.oswald.model.User;
import com.project.design.oswald.sql.DatabaseHelper;

public class SerialActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private final AppCompatActivity activity = SerialActivity.this;
    private User user;
    //   private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_serial);
        ImageButton btn = (ImageButton)findViewById(R.id.next);
        ImageButton btn1 = (ImageButton)findViewById(R.id.back);
        TextView tx= (TextView)findViewById(R.id.serial);
        TextView tx1= (TextView)findViewById(R.id.code);
        final EditText product_id = (EditText)findViewById(R.id.code);
        final String email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(activity);
        if(databaseHelper.getProduct(email)!=null){
            product_id.setText(databaseHelper.getProduct(email));
        }


        // product_id is to be pushed
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        tx.setTypeface(myFont);
        tx1.setTypeface(myFont);
        databaseHelper = new DatabaseHelper(activity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //user.setProduct_id(product_id.getText().toString().trim());
                Intent next = new Intent(SerialActivity.this, NameActivity.class);
                next.putExtra("EMAIL",email);
                String push = product_id.getText().toString().trim();
                if(push == null)
                {
                    Toast.makeText(SerialActivity.this, "Please enter a valid product id", Toast.LENGTH_SHORT).show();
                    Intent back = new Intent(SerialActivity.this, SerialActivity.class);
                    back.putExtra("EMAIL",email);
                    startActivity(back);
                }
                if(databaseHelper.setProduct(email, push)){
                    next.putExtra("EMAIL", email);
                    if(push.length()>7) {
                        startActivity(next);
                    }
                    else
                    {
                        Toast.makeText(SerialActivity.this, "Please enter a valid product id", Toast.LENGTH_SHORT).show();
                        Intent back = new Intent(SerialActivity.this, SerialActivity.class);
                        back.putExtra("EMAIL",email);
                        startActivity(back);
                    }
                }
                else{
                    product_id.setText("unable to set");
                }
                startActivity(next);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SerialActivity.this, WelcomeActivity.class);
                back.putExtra("EMAIL",email);
                startActivity(back);
            }
        });


    }

}
