package com.expensetracking.api;


import com.expensetracking.models.User;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by sandz on 02/27/16.
 */
public interface UserAPI {

    @GET("/user/")
    public Call<User> getUserDetails(@Header("Authorization") String authorization);
}
