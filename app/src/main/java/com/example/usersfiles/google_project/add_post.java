package com.example.usersfiles.google_project;


import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
import com.example.usersfiles.google_project.network.volly;
import com.example.usersfiles.google_project.sharedPreferance.sharedprefarance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class add_post extends android.app.Fragment implements View.OnClickListener {
    int RESULT_LOAD_IMAGE = 1;
    EditText txtPost;
    Button loadImage, btnpost;
    ImageView imageView;
    String url = "http://ultrasahlawy.890m.com/save/insertExperment.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\

        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        txtPost = (EditText) view.findViewById(R.id.txtpost);
        loadImage = (Button) view.findViewById(R.id.btnloadimage);
        btnpost = (Button) view.findViewById(R.id.btnpost);
        imageView = (ImageView) view.findViewById(R.id.image_post);
        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        btnpost.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    @Override
    public void onClick(View view) {
        final sharedprefarance sharedprefarance = new sharedprefarance(getActivity());

        StringRequest request = new StringRequest(StringRequest.Method.POST, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getActivity(),"post added in you timeline", Toast.LENGTH_SHORT).show();

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
                params.put("post", txtPost.getText().toString());
                params.put("photo", "bakr1");
                params.put("date", getdate());
                params.put("user_id", sharedprefarance.getvalue(sharedprefarance.ID_KEY));
                return params;
            }
        };

        volly.getinsance(getActivity()).add_request(request);
    }


    public String getdate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
