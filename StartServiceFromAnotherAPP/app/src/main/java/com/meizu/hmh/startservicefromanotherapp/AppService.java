package com.meizu.hmh.startservicefromanotherapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hmh on 2015/11/29.
 */
public class AppService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("create service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("destroy service");
    }
}
