package com.smartlockinc.smartlocks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by SunnySingh on 9/14/2015.
 */
public class Gcmsessionmanager
{
    static final String DATABASE_NAME = "gcmkey.db";

    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "GCM";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"GCM"+
            "( " +"ID"+" integer primary key autoincrement,"+ "GCMKEY  text not null); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  Gcmsessionmanager(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  Gcmsessionmanager open() throws SQLException
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
    public void insertkey(String gcmkey)
    {

        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put("GCMKEY", gcmkey);



        // Insert the row into your table
        db.insert("GCM", null, newValues);
        //Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();


    }

    // method to delete a Record of UserName
    public int deletekey()
    {
        db  = dbHelper.getReadableDatabase();
        int numberOFEntriesDeleted= db.delete(Gcmsessionmanager.TABLE_NAME,null,null) ;
        //Toast.makeText(context, "Number of key Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;

    }

    // method to get the password  of userName
    public String getkey()
    {
        db  = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(Gcmsessionmanager.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        String key= cursor.getString(cursor.getColumnIndex("GCMKEY"));
        return key;


    }


}