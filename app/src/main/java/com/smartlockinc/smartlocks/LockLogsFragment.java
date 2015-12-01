package com.smartlockinc.smartlocks;

/**
 * Created by SunnySingh on 7/5/2015.
 */

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class LockLogsFragment extends Fragment {
Notificationmessagehandler dbhelper;
    SimpleCursorAdapter dataAdapter;
    public LockLogsFragment() {
        // Required empty public constructor
    }

    public ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle("Lock Logs");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lock_logs, container, false);
        dbhelper = new Notificationmessagehandler(getContext());
        Cursor cursor = dbhelper.getmsg();
        String[] columns = new String[] {dbhelper.colTextup,dbhelper.colMessage,dbhelper.colTextdown,dbhelper.colTime};
        int[] to = new int[]{R.id.textViewup,R.id.Name,R.id.textdown,R.id.Key};
        dataAdapter =new SimpleCursorAdapter(getContext(),R.layout.notificationlistview,cursor,columns,to,0);
        final ListView listview = (ListView) rootView.findViewById(R.id.listView);
        listview.setAdapter(dataAdapter);

        // Inflate the layout for this fragment
        return rootView;
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