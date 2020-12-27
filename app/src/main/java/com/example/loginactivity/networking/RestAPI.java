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

    @POST("/mbanking/api/login/")
    Call<APIResponse> loginNasabah(@Body LoginModel body);

    @POST("/mbanking/api/register/")
    Call<APIResponse> registerNasabah(@Body RegisterModel body);

    @GET("/mbanking/api/saldo/{username}")
    Call<APIResponse> getSaldo(@Path("username")String checkSaldo);

    @POST("/mbanking/api/logout/")
    Call<APIResponse> logoutNasabah();

    @GET("/mbanking/api/nasabah/{username}")
    Call<APIResponse> getNasabah(@Path("username")String username);

    @GET("/mbanking/api/mutasi/{accountnumber}")
    Call<APIResponse> getMutasi(@Path("accountnumber")String cekAccount);

    @GET("/mbanking/api/rekening/{username}")
    Call<APIResponse> getRekening(@Path("username")String username);

}
