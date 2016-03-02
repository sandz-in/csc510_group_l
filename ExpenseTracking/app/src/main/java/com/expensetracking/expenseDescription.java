package com.expensetracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private static final Logger logger= Logger.getLogger(expenseDescription.class.getName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addExpenses = this;

        // Get Intent parameters
        Intent intent = getIntent();
        billAmount=intent.getStringExtra(AddExpenses.BILL_AMOUNT);
        if(billAmount!=null) {
            logger.log(Level.INFO, "Received the billAmount intent parameter with the value : "+billAmount);
        }
        else {
            logger.log(Level.INFO, "Received a null billAmount intent parameter");
        }

        billType=intent.getStringExtra(AddExpenses.EXPENSE_SUBMISSION_METHOD);
        if(billType!=null) {
            logger.log(Level.INFO, "Received the billType intent parameter with the value : "+billType);
        }
        else {
            logger.log(Level.INFO, "Received a null billType intent parameter");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_description);
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
        final EditText editText = (EditText) findViewById(R.id.desc);
        billDesc=editText.getText().toString();
        logger.log(Level.INFO, "Sending the billAmount: "+billAmount+" , billDesc : "+billDesc+", billType : "+billType);
        APIUtils apiUtils = new APIUtils(addExpenses);
        apiUtils.run(billAmount, billDesc, billType);
    }
}
