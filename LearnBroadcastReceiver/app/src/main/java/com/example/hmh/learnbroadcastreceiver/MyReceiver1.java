package com.example.hmh.learnbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hmh on 2015/10/30.
 */
public class MyReceiver1 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("receiver1接受到消息");
        abortBroadcast();

    }
}
