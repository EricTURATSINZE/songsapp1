package com.example.songsapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

import com.example.songsapp.model.User;
import com.example.songsapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FNAME = "user_First_Name";
    private static final String COLUMN_USER_LNAME = "user_Last_Name";
    private static final String COLUMN_USER_EMAIL = "user_Email";
    private static final String COLUMN_USER_PASSWORD = "user_Password";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_FNAME + " TEXT," + COLUMN_USER_LNAME + "TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);

    }

//method to add a user
    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FNAME, user.getFirstName());
        values.put(COLUMN_USER_LNAME, user.getLastName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

//method to update a user
public void updateUser(User user) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(COLUMN_USER_FNAME, user.getFirstName());
    values.put(COLUMN_USER_LNAME, user.getLastName());
    values.put(COLUMN_USER_EMAIL, user.getEmail());
    values.put(COLUMN_USER_PASSWORD, user.getPassword());
    // updating row, where id= whereArgs
    db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
            new String[]{String.valueOf(user.getId())});
    db.close();
}

public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        //List<Contact> contactList = new ArrayList<Contact>();
        Cursor res = null;
        if(db != null) {
            res = db.rawQuery("select * from "+TABLE_USER, null);
        }
        //res.moveToFirst();
        /*while(res.isAfterLast() == false) {
            contactList.add(new Contact(res.getString(res.getColumnIndex("COLUMN_USER_FNAME")), res.getString(res.getColumnIndex("COLUMN_USER_LNAME"))));
        }*/
        return res;
}

//method to delete user record
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
//method to check if user exists in database or not (email and password)

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
     * Here query function is used to fetch records from user table
     * SQL query equivalent to this query function is
     * SELECT user_id FROM user WHERE user_email = ' ' AND user_password = ' ';
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
}