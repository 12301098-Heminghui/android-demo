package com.meizu.scanpictures.config;

import android.graphics.Bitmap;

import com.meizu.hmh.view.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by hmh on 2015/11/4.
 */
public class Config {
    public static final int SCAN_SUCCUSS = 1000;
    //配置图片加载及显示选项
    public static DisplayImageOptions options = new DisplayImageOptions.Builder().
    showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
    .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
    .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
    .bitmapConfig(Bitmap.Config.RGB_565) //避免oom的一个设置
    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
    .imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
    .build();
}
