package com.meizu.scanpictures.view;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by hmh on 2015/11/4.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {

        int memClass = ((ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();//获取可用内存
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(1024 * 1024 * memClass / 8)
                .tasksProcessingOrder(QueueProcessingType.LIFO)//设置加载显示图片队列进程
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}
