package com.smartlockinc.smartlocks;

        import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.CircleImageView;
import com.alexzh.circleimageview.ItemSelectedListener;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class home extends ActionBarActivity
        implements NavigationDrawerCallbacks, ItemSelectedListener {

    AsyncTask<Void, Void, Void> mRegisterTask;
    AlertDialogueManager alert = new AlertDialogueManager();
    ConnectionDetector cd;
    SessionManager session;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private PagerAdapter madapter;
    LoginDataBaseAdapter loginDataBaseAdapter;
    GoogleCloudMessaging gcm;
    photourl uri;
    TextView usrname;
    TextView name;
    CircleImageView img;
    TabLayout mtablayout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        img = (CircleImageView) findViewById(R.id.imgAvatar);
        usrname = (TextView) findViewById(R.id.txtUserEmail);
        name = (TextView) findViewById(R.id.txtUsername);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);
        madapter = new PagerAdapter(getSupportFragmentManager());
        mtablayout = (TabLayout) findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(madapter);
        mtablayout.setTabsFromPagerAdapter(madapter);
        mtablayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtablayout));
        img.setOnItemSelectedClickListener(this);



        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer

            loginDataBaseAdapter = new LoginDataBaseAdapter(home.this);
            String username = loginDataBaseAdapter.getusername();
            String password = loginDataBaseAdapter.getpassword();
            uri = new photourl(home.this);
            Picasso.with(this)
                    .load(uri.geturi())
                    .into(img);
            name.setText(username);
            usrname.setText(password);
        img.setBackgroundColor(Color.TRANSPARENT);




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
                Intent settings = new Intent(home.this,Settings.class);
                startActivity(settings);
                break;
            case 4:
                Intent logout = new Intent(home.this,logout.class);
                startActivity(logout);
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Register()).commit();
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();

    }


    public void unlockbuttonOnClick(View v1) {
        session = new SessionManager(home.this);
        if (session.checklogin() == true) {
            String keyword = "unlock";
            postmethod(this, keyword);
        } else {
            alert.ShowALert(home.this, "Can not unlock", "Login with a valid account", false);
        }

    }

    public void lockbuttonOnClick(View v1) {
        session = new SessionManager(home.this);
        if (session.checklogin() == true) {
            String keyword = "Lock";
            postmethod(this, keyword);
        } else {
            alert.ShowALert(home.this, "Can not lock", "Login with a valid account", false);
        }

    }

    public void postmethod(final Context context, final String keyword) {

        RequestQueue rq = Volley.newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keyword", keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest postReq = new JsonObjectRequest(Request.Method.POST, "http://120.56.230.123:9000/smartlock/action", jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    String data = response.getString("success");
                    Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error [" + error + "]");

            }
        }); /*{
                @Override

                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("keyword",keyword);
                    return params;
                }


            };*/

        rq.add(postReq);


    }


}


