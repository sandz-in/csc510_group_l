package com.expensetracking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class smsBillsActivity extends Activity {

    public static String MSG_EXC="smsBillsActivity.MSG_EXCHANGE";
    private EditText editText;
    private static final Logger logger= Logger.getLogger(smsBillsActivity.class.getName());
    private static String billAmount=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sms_bills);
        Intent intent= getIntent();
        String msg=intent.getStringExtra(smsHomepageActivity.MSG_PARM);
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
            billAmount=number.toString();
        }
    }

    public void updateBill(View view) {
        Intent intent = new Intent(view.getContext(), AddExpenses.class);
        intent.putExtra(MSG_EXC, billAmount);
        startActivity(intent);
    }
}
