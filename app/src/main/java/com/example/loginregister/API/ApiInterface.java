package com.example.loginregister.API;

import com.example.loginregister.APIPost.loginPOST;
import com.example.loginregister.APIPost.signupPOST;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("signup.php")
    public  Call<signupPOST> postPost(@Body signupPOST post);

    @POST("login.php")
    public Call<loginPOST> LpostPost(@Body loginPOST post);


}

