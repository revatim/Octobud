package com.project.design.oswald.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.project.design.oswald.model.User;
import com.project.design.oswald.model.UserD;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_USER1 = "difficulty";

    // User difficulty table

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_CHILD_NAME = "child_name";
    private static final String COLUMN_PRODUCT_KEY = "product_key";
    private static final String COLUMN_CHECK= "check_key";

    // dIFFICULTY Table Columns names
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_EMAIL = "user_email";
    private static final String COLUMN_OPTION_ALPHABETS = "alphabets";
    private static final String COLUMN_OPTION_SENTENCES = "sentences";
    private static final String COLUMN_OPTION_POEMS = "poems";
    private static final String COLUMN_OPTION_COLORS = "colors";
    private static final String COLUMN_OPTION_NUMBERS = "numbers";
    private static final String COLUMN_OPTION_MANNERS = "manners";
    private static final String COLUMN_OPTION_WORDS = "words";

    private static final String COLUMN_OPTION_SP = "special_edition";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_CHILD_NAME + " TEXT,"
            + COLUMN_PRODUCT_KEY + " TEXT," + COLUMN_CHECK + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    // create table sql query
    private String CREATE_USER_TABLE1 = "CREATE TABLE " + TABLE_USER1 + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT," + COLUMN_OPTION_ALPHABETS + " TEXT,"
            + COLUMN_OPTION_WORDS + " TEXT,"
            +COLUMN_OPTION_SENTENCES + " TEXT,"
            +COLUMN_OPTION_POEMS + " TEXT,"
            +COLUMN_OPTION_COLORS + " TEXT,"
            +COLUMN_OPTION_MANNERS + " TEXT,"
            +COLUMN_OPTION_NUMBERS + " TEXT,"
            +COLUMN_OPTION_SP + " TEXT" +
            ")";

    // drop table sql query
    private String DROP_USER_TABLE1 = "DROP TABLE IF EXISTS " + TABLE_USER1;


    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_USER_TABLE1);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        String buf = null;
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        String emails = user.getEmail();
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_CHILD_NAME, buf);
        values.put(COLUMN_PRODUCT_KEY, buf);
        values.put(COLUMN_CHECK, buf);
     //   values.put(COLUMN_CHILD_NAME, User.getChild_name());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        // also add to difficulty level that new user has been added !
        // write code here


        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * +@return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_CHILD_NAME,
                COLUMN_PRODUCT_KEY
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setChild_name(cursor.getString(cursor.getColumnIndex(COLUMN_CHILD_NAME)));
                user.setProduct_id(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_KEY)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_CHILD_NAME, user.getChild_name());
        values.put(COLUMN_PRODUCT_KEY, user.getProduct_id());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
       public String getParentName(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME
        };
        // sorting orders
//        String sortOrder =
//                COLUMN_USER_NAME + " ASC";
//        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String parent_name = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            parent_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
        }

        cursor.close();
        db.close();

        return parent_name;
    }

    // this is to set the childs name
    public boolean setChildName(String email, String child_name) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_CHILD_NAME
        };
        // sorting orders
//        String sortOrder =
//                COLUMN_USER_NAME + " ASC";
//        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,    //columns to return
//                selection,        //columns for the WHERE clause
//                selectionArgs,        //The values for the WHERE clause
//                null,       //group the rows
//                null,       //filter by row groups
//                null); //The sort order
//
//        String parent_name = null;
//        // Traversing through all rows and adding to list
//        if( cursor.moveToFirst()) {
//            parent_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
//        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_CHILD_NAME, child_name);
        int check = db.update(TABLE_USER,
                values,
                selection,
                selectionArgs);

//        cursor.close();
        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    //get child name



    public String getChildName(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_CHILD_NAME
        };
        // sorting orders
//        String sortOrder =
//                COLUMN_USER_NAME + " ASC";
//        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String child_name = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            child_name = cursor.getString(cursor.getColumnIndex(COLUMN_CHILD_NAME));
        }

        cursor.close();
        db.close();

        return child_name;
    }

    public boolean setDone(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_CHECK
        };

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        ContentValues values = new ContentValues();
        values.put(COLUMN_CHECK, "DONE");
        int check = db.update(TABLE_USER,
                values,
                selection,
                selectionArgs);

//        cursor.close();
        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }
    public boolean setProduct(String email, String child_name) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_PRODUCT_KEY
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};


        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_KEY, child_name);
        int check = db.update(TABLE_USER,
                values,
                selection,
                selectionArgs);

//        cursor.close();
        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public String getProduct(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_PRODUCT_KEY
        };
        // sorting orders
//        String sortOrder =
//                COLUMN_USER_NAME + " ASC";
//        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String child_name = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            child_name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_KEY));
        }

        cursor.close();
        db.close();

        return child_name;
    }


    public String getCheck(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_CHECK
        };
        // sorting orders
//        String sortOrder =
//                COLUMN_USER_NAME + " ASC";
//        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /*
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String c = "elo";
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            c = cursor.getString(cursor.getColumnIndex(COLUMN_CHECK));
            String just = c;
        } cursor.moveToNext();

        cursor.close();
        db.close();
        return c;
    }





    // here is difficulty section






    public void addUser1(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String buf = null;
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_OPTION_ALPHABETS, "EASY");
        values.put(COLUMN_OPTION_WORDS, "EASY");
        values.put(COLUMN_OPTION_SENTENCES, "EASY");
        values.put(COLUMN_OPTION_POEMS, "EASY");
        values.put(COLUMN_OPTION_COLORS, "EASY");
        values.put(COLUMN_OPTION_MANNERS, "EASY");
        values.put(COLUMN_OPTION_NUMBERS, "EASY");
        values.put(COLUMN_OPTION_SP, "EASY");

        //   values.put(COLUMN_CHILD_NAME, User.getChild_name());

        // Inserting Row
        db.insert(TABLE_USER1, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     * <p>
     * +@return list
     */
    public List<UserD> getAllUser1() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ID,
                COLUMN_EMAIL,
                COLUMN_OPTION_ALPHABETS,
                COLUMN_OPTION_WORDS,
                COLUMN_OPTION_SENTENCES,
                COLUMN_OPTION_POEMS,
                COLUMN_OPTION_COLORS,
                COLUMN_OPTION_MANNERS,
                COLUMN_OPTION_NUMBERS,
                COLUMN_OPTION_SP

        };
        // sorting orders
        String sortOrder =
                COLUMN_EMAIL + " ASC";
        List<UserD> userList = new ArrayList<UserD>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserD user = new UserD();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser1(UserD user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());

        // updating row
        db.update(TABLE_USER1, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser1(UserD user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER1, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser1(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser1(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
//        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
//        Cursor cursor = db.query(TABLE_USER1, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//
//        int cursorCount = cursor.getCount();
//
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }

        return false;
    }

    //  change the difficulty level
    // syntax here is set...D


    public boolean setAlphabetsD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_ALPHABETS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_ALPHABETS, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setWordsD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_WORDS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_WORDS, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setSentencesD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_SENTENCES
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_SENTENCES, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setPoemsD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_POEMS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_POEMS, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setColorsD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_COLORS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_COLORS, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setMannersD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_MANNERS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_MANNERS, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setNumbersD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_NUMBERS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_NUMBERS, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    public boolean setSpD(String email, String level) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_SP
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_OPTION_SP, level);
        int check = db.update(TABLE_USER1,
                values,
                selection,
                selectionArgs);

        db.close();
        if (check==1)
        {
            return true;
        }
        else
            return false;
    }

    // this part should get the difficulty level of all the 8 sectors

    public String getAlphabetsD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_ALPHABETS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_ALPHABETS));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getWordsD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_WORDS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_WORDS));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getSentencessD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_SENTENCES
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_SENTENCES));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getPoemsD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_POEMS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_POEMS));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getColorsD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_COLORS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_COLORS));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getMannersD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_MANNERS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_MANNERS));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getNumbersD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_NUMBERS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_NUMBERS));
        }

        cursor.close();
        db.close();

        return level;
    }

    public String getSpD(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_EMAIL,
                COLUMN_OPTION_SP
        };

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER1, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String level = null;
        // Traversing through all rows and adding to list
        if( cursor.moveToFirst()) {
            level = cursor.getString(cursor.getColumnIndex(COLUMN_OPTION_SP));
        }

        cursor.close();
        db.close();

        return level;
    }



}
// this is to return the name


