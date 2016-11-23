package com.example.usersfiles.google_project.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.usersfiles.google_project.MainActivity;
import com.example.usersfiles.google_project.R;
import com.example.usersfiles.google_project.network.volly;
import com.example.usersfiles.google_project.sharedPreferance.sharedprefarance;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by UsersFiles on 10/3/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class sign_up extends Fragment implements View.OnClickListener {

    Button sign_in,load_image;
    EditText firstname, lastname, phone, pass, confirmpass;
    AlertDialog.Builder builder;
    String url = "http://ultrasahlawy.890m.com/save/insertDataUser.php";
    private static int RESULT_LOAD_IMAGE = 1;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up, container, false);
        sign_in = (Button) view.findViewById(R.id.btn_singIn);
        load_image= (Button) view.findViewById(R.id.load);
        firstname = (EditText) view.findViewById(R.id.txt_firstName);
        lastname = (EditText) view.findViewById(R.id.txt_lastName);
        phone = (EditText) view.findViewById(R.id.txt_phone);
        pass = (EditText) view.findViewById(R.id.txt_pass);
        confirmpass = (EditText) view.findViewById(R.id.txtconfirmpass);
          imageView = (ImageView) view.findViewById(R.id.imageView);
        builder = new AlertDialog.Builder(getActivity());
        sign_in.setOnClickListener(this);
        load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        //  Toast.makeText(getActivity(), "onclick", Toast.LENGTH_SHORT).show();

        if (check_onInputs()) {
            builder.setTitle("wrong in inputs").setMessage("please fill all inputs");
            show_alert("empty_input");
        } else {
            if (!pass.getText().toString().equals(confirmpass.getText().toString())) {
                builder.setTitle("wrong in Password").setMessage("password must be match");
                show_alert("empty_input");
            } else {
                StringRequest request = new StringRequest(StringRequest.Method.POST, url
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // after sucess sign in
                            //sharedprefarance sharedprefarance = new sharedprefarance(getActivity());
                           // sharedprefarance.add(sharedprefarance.Phone_key, phone.getText().toString());
                            //Toast.makeText(getActivity(), sharedprefarance.getvalue(sharedprefarance.Phone_key), Toast.LENGTH_SHORT).show();
                            ID_get();

                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();


                        } catch (Exception ex) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("first_name", firstname.getText().toString());
                        params.put("last_name", lastname.getText().toString());
                        params.put("pass", pass.getText().toString());
                        params.put("phone", phone.getText().toString());
                        params.put("photo", "photo");
                        params.put("country", "country");
                        params.put("goverment", "goverment");
                        params.put("city", "city");
                        params.put("location_x", "0");
                        params.put("location_y", "0");
                        params.put("token", "token");
                        return params;
                    }
                };

                volly.getinsance(getActivity()).add_request(request);
            }
        }


    }

    public void show_alert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (code.equals("empty_input")) {
                    pass.setText("");
                    confirmpass.setText("");
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean check_onInputs() {

        return phone.getText().toString().isEmpty() ||
                firstname.getText().toString().isEmpty()
                ||
                lastname.getText().toString().isEmpty()
                ||
                confirmpass.getText().toString().isEmpty()
                ||
                pass.getText().toString().isEmpty();

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void ID_get(){
        String url_id ="http://ultrasahlawy.890m.com/save/id_get.php";

        StringRequest request = new StringRequest(StringRequest.Method.POST, url_id
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // after sucess sign in
                    JSONObject jsonObject=new JSONObject(response);
                    String var=jsonObject.getString("id");
                    sharedprefarance sharedprefarance = new sharedprefarance(getActivity());
                    sharedprefarance.add(sharedprefarance.ID_KEY,var);
                    Toast.makeText(getActivity(), sharedprefarance.getvalue(sharedprefarance.ID_KEY), Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();


                } catch (Exception ex) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", phone.getText().toString());

                return params;
            }
        };
        volly.getinsance(getActivity()).add_request(request);
       // sharedprefarance share =new sharedprefarance(getContext());

       // Toast.makeText(getActivity(), share.getvalue(sharedprefarance.ID_KEY), Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver ().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }
}
