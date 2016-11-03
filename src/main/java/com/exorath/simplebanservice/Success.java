package com.exorath.simplebanservice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toonsev on 11/3/2016.
 */
public class Success {
    @SerializedName("success")
    private boolean success;
    @SerializedName("err")
    private String error;
    public Success(boolean success){
        this.success = success;
    }
    public Success(boolean success, String error){
        this.success = success;
        this.error = error;
    }
}
