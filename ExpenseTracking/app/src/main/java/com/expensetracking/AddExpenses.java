package com.expensetracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddExpenses extends AppCompatActivity implements View.OnClickListener {

    private Button one, two, three, four, five, six, seven, eight, nine, zero, decimal, delete, reset;
    private static Button next;
    private EditText input;
    private String m;
   /* public void onClickButtonListener()
    {
        next=(Button) findViewById(R.id.btNext);
        next.setOnClickListener( new View.OnClickListener(){

        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        one = (Button) findViewById(R.id.bt1);
        two = (Button) findViewById(R.id.bt2);
        three = (Button) findViewById(R.id.bt3);
        four = (Button) findViewById(R.id.bt4);
        five = (Button) findViewById(R.id.bt5);
        six = (Button) findViewById(R.id.bt6);
        seven = (Button) findViewById(R.id.bt7);
        eight = (Button) findViewById(R.id.bt8);
        nine = (Button) findViewById(R.id.bt9);
        zero = (Button) findViewById(R.id.bt10);
        decimal = (Button) findViewById(R.id.btDecimal);
        delete = (Button) findViewById(R.id.btReset);
        reset = (Button) findViewById(R.id.btDelete);
        next = (Button) findViewById(R.id.btNext);

        input = (EditText) findViewById(R.id.amount);


        //image intent
        Intent in = getIntent();
        m=in.getStringExtra(ImageClicker.EXE_MSG);


        Editable str = input.getText();
        next.setEnabled(false);

        if(m!=null) {
            input.setText(m);
            next.setEnabled(true);
            input.setSelection(m.length());
        }
        input.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(count>0)
                {
                    if(Double.parseDouble(s.toString())>0)
                        next.setEnabled(true);
                }
                //else if (count==1)
                else if (count > 1 && s.toString().charAt(count)=='.')
                    next.setEnabled(true);

                else
                    next.setEnabled(false);

            }
        });

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
        //getMenuInflater().inflate(R.menu., menu);
        return true;
    }
    public void sendMessage(View view)
    {
        //Do something
       /* Intent intent=new Intent(this, DisplayMessageActivity.class);
        EditText editText=(EditText) findViewById(R.id.edit_message);*/


    }



    @Override
    public void onClick(View arg0)
    {
        Editable str=input.getText();
        switch(arg0.getId())
        {
            case R.id.bt1:
                str=str.append(one.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt2:
                str=str.append(two.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt3:
                str=str.append(three.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt4:
                str=str.append(four.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt5:
                str=str.append(five.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt6:
                str=str.append(six.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt7:
                str=str.append(seven.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt8:
                str=str.append(eight.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt9:
                str=str.append(nine.getText());
                input.setText(str);
                input.setSelection(str.length());
                break;
            case R.id.bt10:
                String text3=str.toString();
                if(text3.length()==1 && text3.compareTo("0")==0)
                    break;
                else {

                    str = str.append(zero.getText());
                    input.setText(str);
                    input.setSelection(str.length());
                }

                break;
            case R.id.btDecimal:
                String text2=input.getText().toString();
                if(text2.length()==0)
                    /*input.setText(str);
                    input.setSelection(0);*/
                    break;


                if(text2.indexOf(".")<0) {
                    str = str.append(decimal.getText());
                    input.setText(str);
                    input.setSelection(str.length());
                }
                break;
            case R.id.btReset:
                input.setText("");
                next.setEnabled(false);
                break;
            case R.id.btDelete:
                int len=str.length();
                if(len>0) {
                    String text = input.getText().toString().trim();
                    text = text.substring(0, len - 1);
                    input.setText(text);
                    input.setSelection(text.length());
                    if(text.length()==0)
                        next.setEnabled(false);
                }

                break;
            case R.id.btNext:
                Intent intent=new Intent("com.expensesharinginterface.expensesharinginterface.expenseDescription");
                startActivity(intent);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

}
