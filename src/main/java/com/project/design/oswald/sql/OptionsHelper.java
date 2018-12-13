package com.project.design.oswald.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.project.design.oswald.R;
import android.widget.MediaController;
import com.project.design.oswald.model.User;
import com.project.design.oswald.model.UserD;

import java.util.ArrayList;
import java.util.List;

public class OptionsHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserOptions.db";

    // User table name
    private static final String TABLE_NAME = "options_data";

    // User Table Columns names
    private static final String COLUMN_USER_ID2 = "user_id";
    private static final String COLUMN_OPTION = "option";

    private static final String COLUMN_FILE_NAME = "file_name";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_LEVEL = "level";
    //private static final String COLUMN_DURATION = "duration";


    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_OPTION + " TEXT,"
            + COLUMN_FILE_NAME + " TEXT,"  + COLUMN_CONTENT + " TEXT," + COLUMN_LEVEL + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * Constructor
     *
     * @param context
     */
    public OptionsHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        addAlphabetsEasy();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);

    }

    public void addAlphabetsEasy()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int S;

        ContentValues values = new ContentValues();

        values.put(COLUMN_OPTION, "ALPHABETS");
        values.put(COLUMN_LEVEL, "EASY");
        values.put(COLUMN_CONTENT,"A");
        S = R.raw.easya ;
        values.put(COLUMN_FILE_NAME,S);
        db.insert(TABLE_NAME, null, values);
        values.clear();

        values.put(COLUMN_OPTION, "ALPHABETS");
        values.put(COLUMN_LEVEL, "EASY");
        values.put(COLUMN_CONTENT,"B");
        S = R.raw.easyb ;
        values.put(COLUMN_FILE_NAME,S);
        db.insert(TABLE_NAME, null, values);
        values.clear();
        db.close();

    }
    public String getAlphabet(String level, String conten){
        String loc= null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_LEVEL + " = ?" ;
        String[] column = {COLUMN_FILE_NAME,
                            COLUMN_CONTENT};
        // selection argument
        String[] selectionArgs = {level};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                column,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        // Traversing through all rows and adding to list
        do {
            if (cursor.moveToFirst()) {
                if (cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)) == conten){

                    loc = cursor.getString(cursor.getColumnIndex(COLUMN_FILE_NAME));
                break;}
            }
        }
        while(cursor.moveToNext());

        cursor.close();
        db.close();


        return loc;
    }


}