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

import com.expensetracking.models.ExpenseItem;
import com.expensetracking.utils.APIUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class expenseDescription extends AppCompatActivity {

    private expenseDescription addExpenses;
    private EditText editText;
    private ExpenseItem expense;

    private static final Logger logger= Logger.getLogger(expenseDescription.class.getName());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addExpenses = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_description);
        String billAmount=null;
        String billDesc=null;
        String billType=null;
        String descBefore=null;

        // Get Intent parameters
        Intent intent = getIntent();
        editText = (EditText) findViewById(R.id.desc);
        billAmount=intent.getStringExtra(AddExpenses.BILL_AMOUNT);
        if(billAmount != null) {
            logger.log(Level.INFO, "Received the billAmount intent parameter with the value : " + billAmount);
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

        descBefore = editText.getText().toString();
        logger.log(Level.INFO, "Initial snapshot of expense description : "+descBefore);

        expense = new ExpenseItem(descBefore, billType);
        expense.setBillAmount(billAmount);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                logger.log(Level.INFO, "before Text changed() called");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                logger.log(Level.INFO, "on Text changed() called, "+s.toString()+"\t"+start+"\t"+before+"\t"+count);
                if(count-before<=0) {
                    //deleteCounts++;
                    expense.incrementDescDelCounterBy(Math.abs(count-before));
                }
                else {
                    // editCounts+=(count-before);
                    expense.incrementDescEditCounterBy(count-before);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                logger.log(Level.INFO, "after Text changed() called, changed to "+s.toString());
                // descAfter = s.toString();
                expense.setBillDesc(s.toString());
            }
        });
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
        logger.log(Level.INFO, "Sending the billAmount: "+expense.getBillAmount()+" ,billType : "+expense.getBillType());
        logTelemetryData();
        APIUtils apiUtils = new APIUtils(addExpenses);
        Double duration = (System.currentTimeMillis() - APIUtils.startTime) / 1000d;
        expense.setTxnDuration(duration);
        apiUtils.addExpense(expense);
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);

    }

    private void logTelemetryData() {
        logger.log(Level.INFO, "TELEMETRY: Initial billDesc : "+expense.getInitBillDesc());
        logger.log(Level.INFO, "TELEMETRY: Final billDesc : "+expense.getBillDesc());
        logger.log(Level.INFO, "TELEMETRY: DESC Delete keystrokes for this expense : "+expense.getDescDelCounter());
        logger.log(Level.INFO, "TELEMETRY: DESC Edit keystrokes for this expense : "+expense.getDescEditCounter());
    }
}
