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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class smsBillsActivity extends Activity {

    public static String MSG_EXC="smsBillsActivity.billAmount";
    public static String SMS_BILL_DESC="smsBillsActivity.billDescription";
    private EditText editText;
    private static final Logger logger= Logger.getLogger(smsBillsActivity.class.getName());
    private String billAmount=null;
    private String billDesc="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sms_bills);
        Intent intent= getIntent();
        String msg=intent.getStringExtra(smsHomepageActivity.MSG_PARM);
        logger.log(Level.INFO, "Received Intent parameter : "+msg);
        editText = (EditText) findViewById(R.id.editText);
        Pattern pattern = Pattern.compile(smsHomepageActivity.AMT_REGEX);
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
        else {
            // Try the raw text regex
            String[] tokens = msg.split(" ");
            Integer counter=0;
            for(String temp: tokens) {
                Matcher matcher = pattern.matcher(temp);
                if (matcher.find()) {
                    editText.setText(matcher.group(0));
                    billAmount=matcher.group(0);
                    break;
                }
            }
            Integer result=0;
            for(;counter<tokens.length && result < 3;counter++) {
                if(isAllUpper(tokens[counter])) {
                    billDesc+=tokens[counter]+" ";
                    result++;
                }
            }
        }
    }

    public void updateBill(View view) {
        Intent intent = new Intent(view.getContext(), AddExpenses.class);
        intent.putExtra(MSG_EXC, billAmount);
        intent.putExtra(SMS_BILL_DESC, billDesc);
        logger.log(Level.INFO, "Sending the following intent : smsBillsActivity to AddExpenses activity with the parameter : "+billAmount);
        startActivity(intent);
    }

    private static boolean isAllUpper(String s) {
        for(char c : s.toCharArray()) {
            if(Character.isLetter(c) && Character.isLowerCase(c)) {
                return false;
            }
            if(Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
