package com.smartlockinc.smartlocks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.CircleImageView;
import com.alexzh.circleimageview.ItemSelectedListener;
import com.squareup.picasso.Picasso;

/**
 * Created by SunnySingh on 9/29/2015.
 */
public class logout extends ActionBarActivity
            implements NavigationDrawerCallbacks, ItemSelectedListener {


        private NavigationDrawerFragment mNavigationDrawerFragment;
        private Toolbar mToolbar;
        LoginDataBaseAdapter loginDataBaseAdapter;
        photourl uri;
        TextView usrname;
        TextView name;
        CircleImageView img;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.logout);
            mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            setSupportActionBar(mToolbar);
            img = (CircleImageView) findViewById(R.id.imgAvatar);
            usrname = (TextView) findViewById(R.id.txtUserEmail);
            name = (TextView) findViewById(R.id.txtUsername);
            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getFragmentManager().findFragmentById(R.id.fragment_drawer);


            // Set up the drawer.
            mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
            img.setOnItemSelectedClickListener(this);

            // populate the navigation drawer
            loginDataBaseAdapter = new LoginDataBaseAdapter(logout.this);
            String username = loginDataBaseAdapter.getusername();
            String password = loginDataBaseAdapter.getpassword();
            uri = new photourl(logout.this);
            Picasso.with(this)
                    .load(uri.geturi())
                    .into(img);
            name.setText(username);
            usrname.setText(password);
            img.setBackgroundColor(Color.TRANSPARENT);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new Signout()).commit();

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            if (!mNavigationDrawerFragment.isDrawerOpen()) {
                // Only show items in the action bar relevant to this screen
                // if the drawer is not showing. Otherwise, let the drawer
                // decide what to show in the action bar.
                getMenuInflater().inflate(R.menu.main, menu);
                return true;
            }
            return super.onCreateOptionsMenu(menu);
        }
    @Override
    public void onUnselected(View view) {
        //
    }
    @Override
    public void onSelected(View view) {
        //
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override

        public void onNavigationDrawerItemSelected(int position) {
            // update the main content by replacing fragments
            switch (position) {
                case 0:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

                    break;

                case 1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LockLogsFragment()).commit();
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SharedKeyFragment()).commit();
                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Signout()).commit();

                    break;
                case 4:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Register()).commit();
                    break;

            }

        }



    }




