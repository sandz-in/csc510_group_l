package com.expensetracking;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Voice extends Activity {

    public static String MSG_EXCHG="Voice.AMOUNT";
    public static String DESC="Voice.DESC";
    protected static final int RESULT_SPEECH = 1;
    private static final Logger logger= Logger.getLogger(Voice.class.getName());

    private ImageButton btnSpeak;
    private TextView txtText;

    public void nextPage(View view) {
        Intent intent = new Intent(view.getContext(), AddExpenses.class);
        String value=txtText.getText().toString();
        if(!isNumeric(value)){
        String amt=value.substring(value.indexOf('$')+1);
        amt=amt.substring(0,amt.indexOf(' '));
        intent.putExtra(MSG_EXCHG, amt);
        logger.info(amt);
        //startActivity(intent);
        String description=(value.substring(value.indexOf("at ")+3)).trim();
        intent.putExtra(DESC,description);
            logger.info(description);
        }
        else{
            intent.putExtra(MSG_EXCHG, value);
        }


        startActivity(intent);
    }

    public boolean isNumeric(String s){
        try
        {
            double d = Double.parseDouble(s);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        txtText = (TextView) findViewById(R.id.txtText);

        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));
                }
                break;
            }

        }
    }
}