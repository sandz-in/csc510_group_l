package com.example.smsFeed;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private static final Logger logger= Logger.getLogger(MyActivity.class.getName());
    private static final String SORT_ORDER="date desc";
    private static final String SMS_INBOX_CONTENT_URI="content://sms/inbox";
    private static final Integer SMS_BODY_INDEX=12;
    private ListView list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button activityButton = (Button) findViewById(R.id.gtSMS);
        list = (ListView) findViewById(R.id.smsView);
        activityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSMSes();
                //sendIntent();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendIntent();
            }
        });

    }

    private void getSMSes() {
        List bills=new ArrayList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        Cursor cursor = getContentResolver().query(Uri.parse(SMS_INBOX_CONTENT_URI), null, null, null, SORT_ORDER);
        Integer retIndex;
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData=cursor.getString(SMS_BODY_INDEX);
                Number number=null;
                try {
                    number = NumberFormat.getCurrencyInstance(Locale.US).parse(msgData);
                } catch(ParseException pe) {
                    number=null;
                }
                if(number!=null) {
                    logger.log(Level.INFO, ": "+msgData+" : matches the regex pattern");
                    adapter.add(msgData);
                    bills.add(msgData);
                }
                // use msgData
                logger.log(Level.INFO, msgData);
            } while (cursor.moveToNext());
            list.setAdapter(adapter);
        } else {
            // empty box, no SMS
        }
    }

    private void sendIntent() {
        Intent intent = new Intent(this, BillsActivity.class);
        startActivity(intent);
    }
}
