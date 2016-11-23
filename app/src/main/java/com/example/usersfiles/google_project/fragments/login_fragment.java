package com.example.usersfiles.google_project.fragments;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

import com.example.usersfiles.google_project.R;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class login_fragment extends Fragment {

    EditText phone, pass;
    Button login;
    AlertDialog.Builder builder;
    TextView signin;

    String url = "http://ultrasahlawy.890m.com/save/login.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        login = (Button) view.findViewById(R.id.btnlognin);
        phone = (EditText) view.findViewById(R.id.txtphone);
        pass = (EditText) view.findViewById(R.id.txtpass);
        signin = (TextView) view.findViewById(R.id.txtsignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to add register fragment
                Toast.makeText(getActivity(), "ONCLIK ", Toast.LENGTH_LONG).show();
                sign_up a = new sign_up();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, a);
                transaction.commit();
            }
        });
        builder = new AlertDialog.Builder(getActivity());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
                    builder.setTitle("inputs is empty").setMessage("please fill all inputs");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            phone.setText("");
                            pass.setText("");
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    StringRequest request = new StringRequest(StringRequest.Method.POST, url
                            , new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String var=jsonObject.getString("bakr");

                                 if (var.equals( "sucess")){
                                     // login success
                                     Toast.makeText(getActivity(), var, Toast.LENGTH_LONG).show();

                                 }


                                if (var.equals( "failed")){
                                    // login failed
                                    Toast.makeText(getActivity(), var, Toast.LENGTH_LONG).show();

                                }
                                 //Toast.makeText(getActivity(), "bakr "+response, Toast.LENGTH_SHORT).show();
                               // Toast.makeText(getActivity(), var, Toast.LENGTH_SHORT).show();

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
                            params.put("pass", pass.getText().toString());
                            return params;
                        }
                    };

                    volly.getinsance(getActivity()).add_request(request);
                }
            }
        });

        return view;
    }


}
