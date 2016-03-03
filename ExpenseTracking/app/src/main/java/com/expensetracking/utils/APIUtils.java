package com.expensetracking.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.expensetracking.DashboardActivity;
import com.expensetracking.api.Expenses;
import com.expensetracking.api.RestAdapter;
import com.expensetracking.models.Result;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sandz on 02/27/16.
 */
public class APIUtils {
    Activity activity;
    private String amount;
    private String description;
    private String billType;

    public APIUtils(Activity activity) {
        this.activity = activity;
    }

    public String getAuthorizationToken() {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(activity.getApplicationContext());

        String auth_token = settings.getString("auth_token", null);
        return auth_token;
    }

    public String getHeaderAuthorizationToken() {
        return " Token " + getAuthorizationToken();
    }

    public boolean addExpense(final String amount, final String description, final String billType) {
        this.amount = amount;
        this.description = description;
        this.billType = billType;

        new Thread(new Runnable() {

            @Override
            public void run() {
                Retrofit restAdapter = new Retrofit.Builder().baseUrl(RestAdapter.API).
                        addConverterFactory(GsonConverterFactory.create()).build();

                Expenses expenses = restAdapter.create(Expenses.class);
                Call<Result> repos1 = expenses.addExpenses(getHeaderAuthorizationToken(), amount, description, billType, "USD");
                repos1.enqueue(new Callback<Result>() {

                    @Override
                    public void onResponse(Response<Result> response, Retrofit retrofit) {
                        Log.i("+==================+", response.message());
                        Result result = response.body();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        }).start();
        return true;
    }


    public boolean addDeleteAction(final String billType) {
        this.description = description;

        new Thread(new Runnable() {

            @Override
            public void run() {
                Retrofit restAdapter = new Retrofit.Builder().baseUrl(RestAdapter.API).
                        addConverterFactory(GsonConverterFactory.create()).build();

                Expenses expenses = restAdapter.create(Expenses.class);
                Call<Result> repos1 = expenses.addDeleteAction(getHeaderAuthorizationToken(), billType);
                repos1.enqueue(new Callback<Result>() {

                    @Override
                    public void onResponse(Response<Result> response, Retrofit retrofit) {
                        Log.i("+==================+", response.message());
                        Result result = response.body();
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        }).start();
        return true;
    }
}

