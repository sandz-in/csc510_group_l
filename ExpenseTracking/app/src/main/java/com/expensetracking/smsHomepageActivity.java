package com.expensetracking;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class smsHomepageActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static final String AMT_REGEX="^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$";
    private static final Logger logger= Logger.getLogger(smsHomepageActivity.class.getName());
    private static final String SORT_ORDER="date desc";
    private static final String SMS_INBOX_CONTENT_URI="content://sms/inbox";
    private static final Integer SMS_BODY_INDEX=12;
    public static final String MSG_PARM="BillAmounts.MSG";
    private ListView list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sms_homepage);
        final Button activityButton = (Button) findViewById(R.id.gtSMS);
        list = (ListView) findViewById(R.id.smsView);
        activityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSMSes();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg=(String) parent.getItemAtPosition(position);
                logger.log(Level.INFO, "Msg is : -----"+msg);
                sendIntent(msg);
            }
        });

    }

    private void getSMSes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        Cursor cursor = getContentResolver().query(Uri.parse(SMS_INBOX_CONTENT_URI), null, null, null, SORT_ORDER);
        Pattern tok = Pattern.compile("\\w+");
        Pattern pattern = Pattern.compile(AMT_REGEX);
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
                    logger.log(Level.INFO, ": "+msgData+" : matches the number format pattern");
                    adapter.add(msgData);
                }
                else {
                    // Try the raw text regex
                    String[] tokens = msgData.split(" ");
                    for(String temp: tokens) {
                        Matcher matcher = pattern.matcher(temp);
                        if (matcher.find()) {
                            logger.log(Level.INFO, msgData+" matches the raw text regex pattern");
                            adapter.add(msgData);
                            break;
                        }
                    }
                }
            } while (cursor.moveToNext());
            list.setAdapter(adapter);
        } else {
            logger.log(Level.INFO, "Empty SMS Inbox");
        }
    }

    private void sendIntent(String msg) {
        Intent intent = new Intent(this, smsBillsActivity.class);
        intent.putExtra(MSG_PARM, msg);
        logger.log(Level.INFO, "Sending the following intent : smsHomepageActivity to smsBillsActivity with the parameter : "+msg);
        startActivity(intent);
    }
}
