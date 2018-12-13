package com.project.design.oswald.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.project.design.oswald.R;
import com.project.design.oswald.helpers.InputValidation;
import com.project.design.oswald.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton appCompatButtonFirstLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Generica.otf");
        textInputLayoutEmail.setTypeface(myFont);
        textInputLayoutPassword.setTypeface(myFont);

        TextView tx = (TextView)findViewById(R.id.textViewLinkRegister);
        Typeface change_font = Typeface.createFromAsset(getAssets(),  "fonts/Generica.otf");
        tx.setTypeface(change_font);


    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        appCompatButtonFirstLogin = (AppCompatButton) findViewById(R.id.appCompatButtonFirstLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        appCompatButtonFirstLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonFirstLogin:
                int i = verifyFromSQLite();
                if(i==1){
                    Intent accountsIntent = new Intent(activity, OrientationActivity.class);
                    // pass on values
//             Intent accountsIntent = new Intent(activity, UsersListActivity.class);
                    accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                    emptyInputEditText();
                    startActivity(accountsIntent);
                }
                break;

            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                int j = verifyFromSQLite();
                if(j==1){
                    String email = textInputEditTextEmail.toString().trim();
                    databaseHelper = new DatabaseHelper(activity);
                    String checkdone = databaseHelper.getCheck(email);
                    String hi = checkdone;

                    Log.i("hihi",checkdone);
                    if(true) {

                        Intent accountsIntent = new Intent(activity, WelcomeBackActivity.class);
                        // pass on values
//             Intent accountsIntent = new Intent(activity, UsersListActivity.class);
                        accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                        emptyInputEditText();
                        startActivity(accountsIntent);
                    }
                    else
                    {
                        Intent accountsIntent = new Intent(activity, OrientationActivity.class);
                        // pass on values
//             Intent accountsIntent = new Intent(activity, UsersListActivity.class);
                        accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                        emptyInputEditText();
                        startActivity(accountsIntent);
                    }
                }
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;

        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private int verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return 0;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return 0;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return 0;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


         return 1;
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            return 0;
        }

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}