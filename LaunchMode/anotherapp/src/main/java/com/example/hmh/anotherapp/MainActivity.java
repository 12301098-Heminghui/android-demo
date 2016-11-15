package com.example.hmh.anotherapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.DomainCombiner;

/**
 * LaunchMode 有四种：Standard(默认情况下) ,SingleTop,SingleTask,SingleInstance(一个任务栈只包含一个activity的实例)
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private TextView mTextView;
    private Button mButtonStart;
    private Button mButtonStop;
    private Button mButtonBind;
    private Button mButtonUnBind;
    private Intent mIntent;
    private EditText mEditText;
    private  Button mButtonSyc;
  //  private MyService.MyBinder mBinder;
    // MyService.MyBinder
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent();
        mIntent.setComponent(new ComponentName("com.example.hmh.launchmode","com.example.hmh.launchmode.Myservice"));
        mTextView = (TextView) findViewById(R.id.textView);
        mButtonStart = (Button)findViewById(R.id.buttonStart);
        mButtonStop = (Button) findViewById(R.id.buttonStop);
        /*mButtonBind = (Button) findViewById(R.id.buttonBind);
        mButtonUnBind = (Button) findViewById(R.id.buttonUnBind);
        mEditText = (EditText) findViewById(R.id.editText);
        mButtonSyc = (Button) findViewById(R.id.buttonSyc);*/
        mButtonStart.setOnClickListener(this);
        mButtonStop.setOnClickListener(this);
       /* mButtonBind.setOnClickListener(this);
        mButtonUnBind.setOnClickListener(this);
        mButtonSyc.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonStart:
               // mIntent.putExtra("data",mEditText.getText().toString());
                startService(mIntent);
                break;
            case R.id.buttonStop:
                stopService(mIntent);
                break;
            /*case R.id.buttonBind:
                bindService(mIntent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.buttonUnBind:
                unbindService(this);
                break;
            case R.id.buttonSyc:
                //unbindService(this);
                if(mBinder != null){
                    mBinder.setData(mEditText.getText().toString());
                }
                break;*/
        }
    }

    /*@Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        System.out.println("System Connected");
        mBinder = (MyService.MyBinder) iBinder;
        mBinder.getService().setCallBack(new MyService.CallBack() {
            @Override
            public void onDataChanged(String str) {
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data",str);
                msg.setData(bundle);
                mHandler.sendMessage(msg);

            }
        });
    }*/

  /*  @Override
    public void onServiceDisconnected(ComponentName componentName) {
    }*/

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String str = bundle.getString("data");
            mTextView.setText(str);

        }
    };
}
