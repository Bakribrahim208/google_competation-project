package com.example.usersfiles.google_project.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by UsersFiles on 10/14/2016.
 */
public class mylocation_listner implements LocationListener {

    Context context;

    public mylocation_listner(Context context){
        this.context=context;
    }
    @Override
    public void onLocationChanged(final Location location) {
        Toast.makeText(context,String.valueOf(location.getLongitude())+String.valueOf(location.getLatitude()),Toast.LENGTH_LONG).show();
        Thread thread=new Thread(){
            @Override
            public void run() {
                ///use location to update user location every time
                Toast.makeText(context,String.valueOf(location.getLongitude())+String.valueOf(location.getLatitude()),Toast.LENGTH_LONG).show();
            }
        };

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
