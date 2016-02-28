package com.example.smsFeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillsActivity extends Activity {

    private EditText editText;
    private static final Logger logger= Logger.getLogger(BillsActivity.class.getName());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bills_activity);
        Intent intent= getIntent();
        String msg=intent.getStringExtra(MyActivity.MSG_PARM);
        logger.log(Level.INFO, "Received Intent parameter : "+msg);
        editText = (EditText) findViewById(R.id.editText);
        Number number=null;
        try {
            number = NumberFormat.getCurrencyInstance(Locale.US).parse(msg);
        } catch(ParseException pe) {
            number=null;
        }
        if(number!=null) {
            editText.setText(number.toString());
        }
    }
}