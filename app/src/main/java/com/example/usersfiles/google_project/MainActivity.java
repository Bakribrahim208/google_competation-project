package com.example.usersfiles.google_project;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.usersfiles.google_project.fragments.login_fragment;
import com.example.usersfiles.google_project.fragments.sign_up;
import com.example.usersfiles.google_project.location.mylocation_listner;
import com.example.usersfiles.google_project.network.volly;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    LocationManager mLocationManager;
    DrawerLayout layout;
    ListView listView;
    String[] List_item;
    ActionBarDrawerToggle toggle;
    String url = "http://ultrasahlawy.890m.com/save/insertDataUser.php";

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        List_item = getResources().getStringArray(R.array.items);
        listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List_item));
        listView.setOnItemClickListener(this);
        toggle = new ActionBarDrawerToggle(this, layout, null, R.string.open, R.string.closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                // Toast.makeText(getApplicationContext(),"open",Toast.LENGTH_LONG).show();
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("الرجوع");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("الشاشه الرئسيه");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.nav));

            }
        };
        toggle.setDrawerIndicatorEnabled(true);
        layout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.nav, null));
        else
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//10 it is time .1 is distance use this to update
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                (long) .1, 10, new mylocation_listner(this));


    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, List_item[i], Toast.LENGTH_LONG).show();
        getSupportActionBar().setTitle(List_item[i]);

       // login_fragment login = new login_fragment();
    //  FragmentTransaction transaction=getFragmentManager().beginTransaction();
       //  transaction.add(R.id.container,login,"a");
      // transaction.commit();


        //    sign_up a =new sign_up();
        // FragmentTransaction transaction=getFragmentManager().beginTransaction();
        // transaction.add(R.id.container,a,"a");
          // transaction.commit();

        add_post post=new add_post();
         FragmentTransaction transaction=getFragmentManager().beginTransaction();
          transaction.add(R.id.container,post,"a");
          transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}
