package com.example.loginregister.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginregister.API.ApiInterface;
import com.example.loginregister.R;
import com.example.loginregister.APIPost.loginPOST;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    TextInputEditText username , password;
    Button buttomlogin ;
    TextView signup ;
    ProgressBar progressBar ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        buttomlogin = findViewById(R.id.button_login);
        signup = findViewById(R.id.registerText);
        progressBar = findViewById(R.id.progress1);

        loginPOST loginPOST = new loginPOST(username.getText().toString(),password.getText().toString() );


        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.22.208.1/loginregister/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<loginPOST> call = apiInterface.LpostPost(loginPOST);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signUp.class);
                   startActivity(intent);
                  finish();
            }
        });

        buttomlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<com.example.loginregister.APIPost.loginPOST>() {
                    @Override
                    public void onResponse(Call<com.example.loginregister.APIPost.loginPOST> call, Response<com.example.loginregister.APIPost.loginPOST> response) {
                        progressBar.setVisibility(View.GONE);
                        loginPOST loginPOST=  new loginPOST(response.message());
                        Log.d(TAG, "Request done is " + response);


                        Toast.makeText(login.this,loginPOST.getMassage() , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<com.example.loginregister.APIPost.loginPOST> call, Throwable t) {
                        Log.d(TAG, "Request failure is " + t);
                        Toast.makeText(login.this,"fail" , Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });




    }
}