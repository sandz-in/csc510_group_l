package com.example.smsFeed;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private static final Logger logger= Logger.getLogger(MyActivity.class.getName());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button activityButton = (Button) findViewById(R.id.gtSMS);
        activityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSMSes();
            }
        });
    }

    private void getSMSes() {
        final ListView list = (ListView) findViewById(R.id.smsView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        Integer retIndex;
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                 retIndex = cursor.getColumnIndexOrThrow("body");
                for(int idx=0;idx<cursor.getColumnCount();idx++) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                adapter.add(msgData);
                // use msgData
                logger.log(Level.INFO, msgData);
            } while (cursor.moveToNext());
            list.setAdapter(adapter);
        } else {
            // empty box, no SMS
        }
    }
}
