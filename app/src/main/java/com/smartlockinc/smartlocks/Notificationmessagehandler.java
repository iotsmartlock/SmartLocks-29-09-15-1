package com.smartlockinc.smartlocks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SunnySingh on 10/15/2015.
 */
public class Notificationmessagehandler {
    static final String DATABASE_NAME = "notification.db";

    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "NOTIFICATION";
    public static final String colMessage="_id";
    public static final String colTime="Time";
    public static final String colTextup="up";
    public static final String colTextdown="down";



    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS NOTIFICATION(up VARCHAR,down VARCHAR,_id VARCHAR, Time VARCHAR);";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  Notificationmessagehandler(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  Notificationmessagehandler open() throws SQLException
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
    public void insertmsg(String msg, String time,String up, String down)
    {

        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put("_id", msg);
        newValues.put("Time", time);
        newValues.put("up", up);
        newValues.put("down", down);


        // Insert the row into your table
        db.insert("NOTIFICATION", null, newValues);
        //Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();


    }



    // method to get the password  of userName
    public Cursor getmsg()
    {
        db  = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NOTIFICATION;", null);
        return cursor;


    }


}

