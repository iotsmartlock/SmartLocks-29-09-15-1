package com.smartlockinc.smartlocks;

/**
 * Created by SunnySingh on 9/19/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                LockLogsFragment tab2 = new LockLogsFragment();
                return tab2;
            case 2:
                SharedKeyFragment tab3 = new SharedKeyFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3 ;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position) {
            case 0:
                return "       Home       ";
            case 1:
                return "     LockStatus    ";
            case 2:
                return "   Family & Friends  ";
            default:
                return null;
        }
    }
}