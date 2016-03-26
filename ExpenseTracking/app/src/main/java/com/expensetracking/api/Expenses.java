package com.expensetracking.api;

import com.expensetracking.models.Expense;
import com.expensetracking.models.Result;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by sandz on 02/27/16.
 */
public interface Expenses {

    @GET("/api/expenses/")
    public Call<List<Expense>> getExpenses(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("/api/expense/add/")
    public Call<Result> addExpenses(@Header("Authorization") String authorization,
                                    @Field("amount") String amount,
                                    @Field("description") String description,
                                    @Field("billtype") String billtype,
                                    @Field("currency") String currency,
                                    @Field("duration") double duration);

    @FormUrlEncoded
    @POST("/api/action/delete/")
    public Call<Result> addDeleteAction(@Header("Authorization") String authorization, @Field("billtype") String billtype);

}

