package com.expensetracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.logging.Level;
import java.util.logging.Logger;

public class expenseDescription extends AppCompatActivity {

    private String billAmount=null;
    private String billDesc=null;
    private static final Logger logger= Logger.getLogger(expenseDescription.class.getName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Get Intent parameters
        Intent intent = getIntent();
        billAmount=intent.getStringExtra(AddExpenses.BILL_AMOUNT);

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
        logger.log(Level.INFO, "Sending the billAmount: "+billAmount+" and billDesc : "+billDesc);
    }

}
