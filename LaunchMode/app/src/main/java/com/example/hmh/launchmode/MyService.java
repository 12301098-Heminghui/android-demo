package com.example.hmh.launchmode;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private boolean isRunning = false;
    private String data = "默认信息";
    private final IBinder binder = new MyBinder();

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      //  throw new UnsupportedOperationException("Not yet implemented");
        return  binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
     /*   isRunning = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while(isRunning){
                    i++;
                    String str = i + ":" + data;
                    if(mCallBack != null){
                        mCallBack.onDataChanged(str);
                    }
                    System.out.println(str);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();*/
        System.out.println("service create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        System.out.println("service destroy");
    }

    public class MyBinder extends android.os.Binder{
        public void setData(String data){
            MyService.this.data = data;
        }
        public MyService getService(){
            return MyService.this;
        }


    }

    public interface  CallBack{
        //回调函数
        public void onDataChanged(String str);
    }
    private CallBack mCallBack;
    public void setCallBack(CallBack callBack){
        mCallBack = callBack;
    }
}
