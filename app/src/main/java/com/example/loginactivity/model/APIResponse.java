package com.example.loginactivity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("response")
    @Expose
    private int response;

    @SerializedName("message")
    @Expose
    private String message;

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }


    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
