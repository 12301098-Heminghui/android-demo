package com.example.hmh.learnbroadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnSendMsg;
    private Button mBtnRegister;
    private Button mBtnUnRegister;
    private MyReceiver myReceiver = null;
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnSendMsg = (Button) findViewById(R.id.btnSendMsg);
        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mBtnUnRegister = (Button) findViewById(R.id.btnUnRegister);
        mBtnSendMsg.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mBtnUnRegister.setOnClickListener(this);
        Log.e(TAG, "错误信息");
        Log.w(TAG, "警告信息");
        Log.i(TAG, "普通信息");
        Log.d(TAG, "调试信息");
        Log.v(TAG, "无用信息");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSendMsg:
              //  sendBroadcast(new Intent(MyReceiver.ACTION));
                sendOrderedBroadcast(new Intent(MyReceiver.ACTION) , null);
                break;
            case R.id.btnRegister:
                if(myReceiver == null){
                    myReceiver = new MyReceiver();
                    registerReceiver(myReceiver,new IntentFilter(MyReceiver.ACTION));
                }
                break;
            case R.id.btnUnRegister:

                if(myReceiver != null){
                    unregisterReceiver(myReceiver);
                    myReceiver = null;
                }
        }
    }
}
