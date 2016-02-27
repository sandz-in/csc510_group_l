package com.expensetracking.api;

import com.expensetracking.models.Expense;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by sandz on 02/27/16.
 */
public interface Expenses {

    @GET("/api/expenses/")
    public Call<List<Expense>> getExpenses(@Header("Authorization") String authorization);
}

