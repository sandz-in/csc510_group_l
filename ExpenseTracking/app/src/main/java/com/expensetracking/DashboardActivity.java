package com.expensetracking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.expensetracking.api.RestAdapter;
import com.expensetracking.api.UserAPI;
import com.expensetracking.models.User;
import com.expensetracking.utils.APIUtils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class DashboardActivity extends Activity {

    private static final Logger logger= Logger.getLogger(DashboardActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(RestAdapter.API).
                addConverterFactory(GsonConverterFactory.create()).build();

        UserAPI cfu = restAdapter.create(UserAPI.class);

        APIUtils apiUtils = new APIUtils(this);
        String authorizationToken = apiUtils.getHeaderAuthorizationToken();
        Call<User> repos = cfu.getUserDetails(authorizationToken);


        repos.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
//                User user = response.body();
//                Log.w("User response", user.getEmail());
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });
        setContentView(R.layout.activity_dashboard);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically 0handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            new Thread(new Runnable() {

                public void run() {
                    logout();
                }
            }).start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addExpenses(View view) {
        APIUtils.startTime = System.currentTimeMillis();
        Intent intent = new Intent(view.getContext(), AddExpenses.class);
        logger.log(Level.INFO, "Sending the following intent : DashboardActivity to AddExpenses activity");
        startActivity(intent);
    }

    public void createCameraActivity(View view) {
        APIUtils.startTime = System.currentTimeMillis();
        Intent intent = new Intent(view.getContext(), ImageClicker.class);
        logger.log(Level.INFO, "Sending the following intent : DashboardActivity to Image Scan activity");
        startActivity(intent);
    }

    public void smsView(View view) {
        APIUtils.startTime = System.currentTimeMillis();
        Intent intent = new Intent(view.getContext(), smsHomepageActivity.class);
        logger.log(Level.INFO, "Sending the following intent : DashboardActivity to smsView activity");
        startActivity(intent);
    }

    public void voiceExpenses(View view) {
        APIUtils.startTime = System.currentTimeMillis();
        Intent intent = new Intent(view.getContext(), Voice.class);
        logger.log(Level.INFO, "Sending the following intent : DashboardActivity to voiceExpenses activity");
        startActivity(intent);
    }

    public void viewExpenses(View view) {
//        Intent intent = new Intent(view.getContext(), AddExpenses.class);
//        startActivity(intent);
    }

    private void logout() {
        try {
            HttpPost hp = new HttpPost(RestAdapter.URL + "/auth/logout/");
            APIUtils apiUtils = new APIUtils(this);
            String authorizationToken = apiUtils.getHeaderAuthorizationToken();

            hp.setHeader("Authentication", authorizationToken);
            HttpClient ahc = new DefaultHttpClient();

            ahc.execute(hp);
            SharedPreferences settings = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = settings.edit();
            editor.remove("auth_token");
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
            startActivity(intent);
        } catch (IOException e) {

        }

    }
}
