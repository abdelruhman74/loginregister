package com.example.loginregister.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import com.example.loginregister.APIPost.signupPOST;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signUp extends AppCompatActivity {

    TextInputEditText textfullname ,textusername , textpassword , textemail ;
    Button signupButton;
    TextView loginText ;
    ProgressBar progressBar ;

    String a , b , c , d ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textemail = findViewById(R.id.email);
        textfullname = findViewById(R.id.fullname);
        textpassword = findViewById(R.id.password);
        textusername = findViewById(R.id.username);
        progressBar = findViewById(R.id.progress);
        signupButton = findViewById(R.id.buttonSignUp);
        loginText = findViewById(R.id.loginText);
        String return1 ;
        a = "Sign Up Success";
        b= "Sign up Failed" ;
        c ="Error: Database connection";
        d="All fields are required" ;

        signupPOST signupPOST = new signupPOST(textfullname.getText().toString() ,textusername.getText().toString() ,textemail.getText().toString() ,textpassword.getText().toString()) ;

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .connectTimeout(100,TimeUnit.SECONDS)
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.22.208.1/loginregister/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<signupPOST> call = apiInterface.postPost(signupPOST );


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);







                call.enqueue(new Callback<com.example.loginregister.APIPost.signupPOST>() {
                    @Override
                    public void onResponse(Call<com.example.loginregister.APIPost.signupPOST> call, Response<com.example.loginregister.APIPost.signupPOST> response) {
                        progressBar.setVisibility(View.GONE);
                        signupPOST signupPOST=  new signupPOST(response.message());
                      String  massage = signupPOST.getMassage();
                        Log.d(TAG, "Request done is " + response);

                        Toast.makeText(signUp.this,massage , Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        //   startActivity(intent);
                        //  finish();
                    }

                    @Override
                    public void onFailure(Call<com.example.loginregister.APIPost.signupPOST> call, Throwable t) {
                        Toast.makeText(signUp.this,"fail" , Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Request failure is " + t);

                    }
                });


            }
        });
    }
}