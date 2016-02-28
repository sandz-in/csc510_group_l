package com.expensetracking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

public class ImageClicker extends AppCompatActivity {
    public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/tee/";
    public static final String lang = "eng";
    private static final String TAG = "ImageClicker.java";
    public static String EXE_MSG = " Image message";

    Button btnClick;
    ImageView imgTakenPic;

    private static final int CAM_REQUEST = 1313;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAM_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
             imgTakenPic.setImageBitmap(thumbnail);
            TessBaseAPI baseApi = new TessBaseAPI();
            baseApi.setDebug(true);
//            String[] paths = new String[]{DATA_PATH, DATA_PATH + "tessdata/"};
            try {
//                for (String path : paths) {
//                    File dir = new File(path);
//                    Log.i("x,", "PATH ==" + path);
//                    Log.i(TAG, path + " " + dir.exists());
//                    if (!dir.exists()) {
//                        if (!dir.mkdirs()) {
//                        } else {
//                            Log.v(TAG, "Created directory " + path + " on sdcard");
//                        }
//                    }
//                }

                baseApi.init(DATA_PATH, lang);
                if (baseApi != null) { //NOTEEE
                    String characterWhitelist = "$.,;:'\"0123456789";
                    String characterBlacklist = "";
//                    baseApi.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, characterBlacklist); //ERROR!!
//                    baseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, characterWhitelist);
                }

            } catch (Exception e) {
                Log.e("sd", e.getMessage());
                e.printStackTrace();
            }
            baseApi.setImage(thumbnail);
            String x = baseApi.getUTF8Text();
            Log.i("dd", x == null ? "dddd" : x);
            String s;
            if(x==null)
            {
                s="dddd";
            }
            else
            {
                s=x;
            }

            Intent in = new Intent(this,AddExpenses.class);
            EditText editMessage = (EditText)findViewById(R.id.amount);
            TextView textView = (TextView)findViewById(R.id.textView2);
          //  textView.setText(s);
            in.putExtra(EXE_MSG,s);
            startActivity(in);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_clicker);
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


        btnClick = (Button) findViewById(R.id.button1);
        imgTakenPic = (ImageView) findViewById(R.id.imageview1);

        btnClick.setOnClickListener(new btnClickClicker());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_clicker, menu);
        return true;
    }


    class btnClickClicker implements Button.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAM_REQUEST);
        }
    }
}
