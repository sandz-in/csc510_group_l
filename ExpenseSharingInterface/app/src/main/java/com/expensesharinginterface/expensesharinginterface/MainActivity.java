package com.expensesharinginterface.expensesharinginterface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button one, two, three, four, five, six, seven, eight, nine, zero, decimal, delete, reset, next;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one=(Button) findViewById(R.id.bt1);
        two=(Button) findViewById(R.id.bt2);
        three=(Button) findViewById(R.id.bt3);
        four=(Button) findViewById(R.id.bt4);
        five=(Button) findViewById(R.id.bt5);
        six=(Button) findViewById(R.id.bt6);
        seven=(Button) findViewById(R.id.bt7);
        eight=(Button) findViewById(R.id.bt8);
        nine=(Button) findViewById(R.id.bt9);
        zero=(Button) findViewById(R.id.bt10);
        decimal=(Button) findViewById(R.id.btDecimal);
        delete=(Button) findViewById(R.id.btReset);
        reset=(Button) findViewById(R.id.btDelete);
        next=(Button) findViewById(R.id.btNext);
        input=(EditText) findViewById(R.id.amount);
        try
        {
            one.setOnClickListener(this);
            two.setOnClickListener(this);
            three.setOnClickListener(this);
            four.setOnClickListener(this);
            five.setOnClickListener(this);
            six.setOnClickListener(this);
            seven.setOnClickListener(this);
            eight.setOnClickListener(this);
            nine.setOnClickListener(this);
            zero.setOnClickListener(this);
            decimal.setOnClickListener(this);
            delete.setOnClickListener(this);
            reset.setOnClickListener(this);
            next.setOnClickListener(this);

        }
        catch(Exception e)
        {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View arg0)
    {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
