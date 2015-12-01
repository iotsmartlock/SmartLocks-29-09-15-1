package com.smartlockinc.smartlocks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
/**
 * Created by SunnySingh on 7/5/2015.
 */
public class Register extends Fragment  {

    public Register() {
        // Required empty public constructor
    }

    public ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle("Register");
        Intent i = new Intent(getActivity().getApplicationContext(),Signupstartup.class);
        startActivity(i);
        getActivity().finish();

    }






    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
