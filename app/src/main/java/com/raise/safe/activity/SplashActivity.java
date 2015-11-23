package com.raise.safe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SplashActivity extends Activity {

    Handler m_handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(SplashActivity.this, "" + msg.obj, Toast.LENGTH_SHORT).show();
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkVersion();
    }

    private void checkVersion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = m_handler.obtainMessage();
                try {
                    URL url = new URL("http://192.168.50.128:8080/examples/version.json");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setConnectTimeout(3000);
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String s = br.readLine();
                    msg.obj = "连接成功" + s;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.obj = "连接失败";
                }
                msg.sendToTarget();
            }
        }).start();
    }
}
