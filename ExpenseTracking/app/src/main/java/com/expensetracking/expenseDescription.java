package com.expensetracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.expensetracking.utils.APIUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class expenseDescription extends AppCompatActivity {

    private String billAmount=null;
    private String billDesc=null;
    private String billType=null;
    private expenseDescription addExpenses;
    private EditText editText;
    private String descBefore="";
    private String descAfter="";
    private Integer deleteCounts=0;
    private Integer editCounts=0;
    private static final Logger logger= Logger.getLogger(expenseDescription.class.getName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addExpenses = this;
        deleteCounts=0;
        editCounts=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_description);

        // Get Intent parameters
        Intent intent = getIntent();
        editText = (EditText) findViewById(R.id.desc);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                logger.log(Level.INFO, "before Text changed() called");
                descBefore = editText.getText().toString();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                logger.log(Level.INFO, "on Text changed() called, "+s.toString()+"\t"+start+"\t"+before+"\t"+count);


            }

            @Override
            public void afterTextChanged(Editable s) {
                logger.log(Level.INFO, "after Text changed() called, changed to "+s.toString());
                descAfter = s.toString();
            }
        });


        billAmount=intent.getStringExtra(AddExpenses.BILL_AMOUNT);
        if(billAmount != null) {
            logger.log(Level.INFO, "Received the billAmount intent parameter with the value : "+billAmount);
        }
        else {
            logger.log(Level.INFO, "Received a null billAmount intent parameter");
        }

        billType=intent.getStringExtra(AddExpenses.EXPENSE_SUBMISSION_METHOD);
        if (billType != null) {
            logger.log(Level.INFO, "Received the billType intent parameter with the value : " + billType);
        }
        else {
            logger.log(Level.INFO, "Received a null billType intent parameter");
        }

        billDesc=intent.getStringExtra(AddExpenses.EXPENSE_DESCRIPTION);
        if(billDesc!=null) {
            logger.log(Level.INFO, "Received the billDesc intent parameter with the value : "+billDesc);
            editText.setText(billDesc);
        }
        else {
            logger.log(Level.INFO, "Received a null billDesc intent parameter");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void postDetails(View view) {
        billDesc=editText.getText().toString();
        logger.log(Level.INFO, "Sending the billAmount: "+billAmount+" , billDesc : "+billDesc+", billType : "+billType);
        APIUtils apiUtils = new APIUtils(addExpenses);
        apiUtils.run(billAmount, billDesc, billType);
    }
}
