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
public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";

    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "LOGIN";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text not null,PASSWORD text not null); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  LoginDataBaseAdapter open() throws SQLException
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
    public void insertEntry(String userName,String password)
    {

        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);



        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        //Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();


    }

    // method to delete a Record of UserName
    public int deleteEntry()
    {
        db  = dbHelper.getReadableDatabase();
        int numberOFEntriesDeleted= db.delete(LoginDataBaseAdapter.TABLE_NAME,null,null) ;
        //Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;

    }

    // method to get the password  of userName
    public String getpassword()
    {
        db  = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(LoginDataBaseAdapter.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        return password;


    }
    public String getusername()
    {
        db  = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(LoginDataBaseAdapter.TABLE_NAME, null, null, null, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        String username= cursor.getString(cursor.getColumnIndex("USERNAME"));
        return username;


    }

    // Method to Update an Existing Record
    public void  updateEntry(String userName,String password)
    {
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});

    }


}