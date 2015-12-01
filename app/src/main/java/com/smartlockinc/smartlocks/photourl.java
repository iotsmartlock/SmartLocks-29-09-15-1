package com.smartlockinc.smartlocks;

/**
 * Created by SunnySingh on 7/15/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class photourl
{
    static final String DATABASE_NAME = "pic.db";

    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "PIC";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"PIC"+
            "( " +"ID"+" integer primary key autoincrement,"+ "URI  text not null); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  photourl(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  photourl open() throws SQLException
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
    public void inserturi(String uri)
    {

        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put("URI", uri);



        // Insert the row into your table
        db.insert("PIC", null, newValues);
        //Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();


    }

    // method to delete a Record of UserName
    public int deleteuri()
    {
        db  = dbHelper.getReadableDatabase();
        int numberOFEntriesDeleted= db.delete(photourl.TABLE_NAME,null,null) ;
        //Toast.makeText(context, "Number of key Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;

    }

    // method to get the password  of userName
    public String geturi()
    {
        db  = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(photourl.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        String key= cursor.getString(cursor.getColumnIndex("URI"));
        return key;


    }


}