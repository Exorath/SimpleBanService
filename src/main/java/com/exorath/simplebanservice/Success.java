package com.exorath.simplebanservice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toonsev on 11/3/2016.
 */
public class Success {
    @SerializedName("success")
    private boolean success;
    public Success(boolean success){
        this.success = success;
    }
}
