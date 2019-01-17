package com.example.zouxianbin.bignews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zouxianbin.news.BigNews;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    String PATH_1_0 = SD_PATH + "/a/app-1.0.apk";//旧版本
    String PATH_2_0 = SD_PATH + "/a/app-2.0.apk";//新版本
    String PATCH_PATH = SD_PATH + "/a/app_patch.patch";//旧版本与新版本生成的差异包
    String PATH_1_0_2_0 = SD_PATH + "/a/app-1.0-2.0.apk";//旧版本与差异包合成的新apk


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.v);
        Button button1 = findViewById(R.id.button);
        TextView button2 = findViewById(R.id.button2);

        textView.setText("code=" + APKVersionCodeUtils.getVersionCode(this) + "       name=" + APKVersionCodeUtils.getVerName(this));


        //生成

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        //合成事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new BsPatchUpdateTask().execute();
            }
        });

    }


    //合成
    private class BsPatchUpdateTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {


            File old_apk = new File(PATH_1_0);
            if (!old_apk.exists()) {


                return null;
            }


            File patch = new File(PATCH_PATH);
            if (!patch.exists()) {


                return null;
            }

            File PATH_APK = new File(PATH_1_0_2_0);
            if (PATH_APK.exists()) {



                return null;
            }
            final boolean b = BigNews.bspatch(PATH_1_0, PATCH_PATH, PATH_1_0_2_0);

            Log.e("TAG", "b=" + b);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,""+b,Toast.LENGTH_LONG).show();
                }
            });



            return null;
        }
    }




}
