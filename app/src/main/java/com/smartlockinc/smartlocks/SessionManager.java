package com.smartlockinc.smartlocks;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by SunnySingh on 7/5/2015.
 */
public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE=0;
    private static final String PREF_NAME = "Smartlock";
    public static final String Flag = "";
    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void atlogin(String flag)
    {
        editor.putString(Flag, flag);
        editor.commit();
    }
    public String getstring()
    {
        return pref.getString(Flag,null);
    }




    public boolean checklogin()
    {
        if(pref.getString(Flag,null)!=null)
        {
            return true;
        }
        else
            return false;


    }

    public void atlogout()
    {
        editor.clear();
        editor.commit();

    }




}
