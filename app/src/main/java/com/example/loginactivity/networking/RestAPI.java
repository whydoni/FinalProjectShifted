package com.example.loginactivity.networking;

import com.example.loginactivity.model.LoginModel;
import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {

    @POST("/login/")
    Call<APIResponse> loginNasabah(@Body LoginModel body);

    @POST("/nasabah/")
    Call<APIResponse> registerNasabah(@Body RegisterModel body);

    @GET("/saldo/{username}")
    Call<APIResponse> getSaldo(@Path("username")String checkSaldo);

    @POST("/logout/")
    Call<APIResponse> logoutNasabah();

    @GET("/mutasi/{accountnumber}")
    Call<APIResponse> getMutasi(@Path("accountnumber")String accountnumber);

}
