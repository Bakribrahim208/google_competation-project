package com.example.usersfiles.google_project.sharedPreferance;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by UsersFiles on 10/14/2016.
 */
public class sharedprefarance  {
  public static String  ID_KEY ="phone";



    String Preference_name = "save_Preference";
    SharedPreferences sharedPreference;

    public sharedprefarance(Context context) {
        sharedPreference = context.getSharedPreferences(Preference_name, Context.MODE_PRIVATE);

    }

    public  void add(String key,String value){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key,value);
        editor.commit();
    }
          public String getvalue(String key){
              return  sharedPreference.getString(key,"no result");
          }

}
