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

    public boolean run(final String amount, final String description) {
        this.amount = amount;
        this.description = description;

        new Thread(new Runnable() {

            @Override
            public void run() {
                Retrofit restAdapter = new Retrofit.Builder().baseUrl(RestAdapter.API).
                        addConverterFactory(GsonConverterFactory.create()).build();

                Expenses expenses = restAdapter.create(Expenses.class);
                Call<Result> repos1 = expenses.addExpenses(getHeaderAuthorizationToken(), amount, description, "USD");
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
//
//                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//                postParameters.add(new BasicNameValuePair("amount", 1.2 + ""));
//                postParameters.add(new BasicNameValuePair("description", "dddd"));
//                postParameters.add(new BasicNameValuePair("currency", "USD"));
//
//                String response = null;
//                try {
//                    HttpPost hp = new HttpPost("http://expensetracking.herokuapp.com/api/expense/add/");
//                    hp.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
//                    HttpClient ahc = new DefaultHttpClient();
//
//                    HttpResponse response1 = ahc.execute(hp);
//
//                    String res = EntityUtils.toString(response1.getEntity());
//                    JSONObject jsonObject = new JSONObject(res);
//
//                    String token = (String) jsonObject.get("auth_token");
//                    Log.w("Auth oken", "AUth token : " + token);
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }).start();
        return true;
    }
}

