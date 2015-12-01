package com.smartlockinc.smartlocks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;

import java.sql.Blob;

/**
 * Created by SunnySingh on 10/9/2015.
 */
public class Memberhandler { static final String DATABASE_NAME = "member.db";

    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "MEMBER";
    public static final String colName="_id";
    public static final String colTextup="up";
    public static final String colTextdown="down";
    public static final String colKey="Key";


    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    //static final String DATABASE_CREATE = "create table "+"MEMBER"+ "( " +"NAME"+" text,"+ "RASPKEY  text); ";
    static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS MEMBER(up VARCHAR,down VARCHAR,_id VARCHAR, Key VARCHAR);";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  Memberhandler(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  Memberhandler open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table
    public void insertmember(String up,String name,String down,String raspkey)
    {

        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put("_id", name);
        newValues.put("Key", raspkey);
        newValues.put("up", up);
        newValues.put("down", down);



        // Insert the row into your table
        db.insert("MEMBER", null, newValues);
        //Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();


    }



    // method to get the password  of userName
    public Cursor getmembername()
    {
        db  = dbHelper.getReadableDatabase();
        //Cursor cursor=db.query(Memberhandler.TABLE_NAME,null,null,null,null,null,null);
        Cursor cursor = db.rawQuery("SELECT * FROM MEMBER;",null);
        return cursor;


    }
    public String getnameof()
    {
        db  = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(Memberhandler.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        String key= cursor.getString(cursor.getColumnIndex("_id"));
        return key;



    }


}

