package com.meizu.scanpictures.view;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.meizu.hmh.view.R;
import com.meizu.scanpictures.bean.FolderBean;
import com.meizu.scanpictures.config.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ProgressDialog mProgressDialog;
    private HashMap<String, List<String>> mHashMap = new HashMap<String, List<String>>();//存储相同文件夹下的图片
    private List<FolderBean> mBeanList = new ArrayList<FolderBean>();//存储图片文件夹下的第一张图片信息
   // private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.mainActivity_tip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView = (ListView) findViewById(R.id.listView);
        getImages();

        //点击item，以九宫格形式显示图片
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                List<String> childList = mHashMap.get(mBeanList.get(position).getFolderName());
                Intent intent = new Intent(MainActivity.this, ShowImagesActivity.class);
                intent.putStringArrayListExtra("childList", (ArrayList<String>) childList);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 利用contentProvider 扫描手机里的图片
     */
    private void getImages() {
        //判断当前存储卡可不可用(判断SD卡是否存在，并且是否具有读写权限)
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "当前存储卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        //显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载图片");

        new Thread(new Runnable() {
            @Override
            public void run() {

                Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = MainActivity.this.getContentResolver();
                Cursor cursor = contentResolver.query(imgUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image.jpeg","image.png"},MediaStore.Images.Media.DATE_MODIFIED);
                if(cursor == null){
                    return;
                }

                while (cursor.moveToNext()){
                    //获取图片的路径
                    String imgPath = "file://"+ cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    System.out.println("imgPath:" + imgPath);
                    File parentFile = new File(imgPath).getParentFile();
                    //获取图片所在文件夹名称和路径
                    String folderName = parentFile.getName();
                    String folderPath = parentFile.getAbsolutePath();
                    System.out.println("folderPath：" + folderPath);
                    //根据父路径名将图片放入到mGruopMap中

                    if(mHashMap.containsKey(folderName)){
                        mHashMap.get(folderName).add(imgPath);
                    }else{
                        //创建一个list
                        List<String> childList = new ArrayList<String>();
                        childList.add(imgPath);
                        mHashMap.put(folderName,childList);
                    }
                }
                //通知Handler扫描图片完成
                mHandler.sendEmptyMessage(Config.SCAN_SUCCUSS);
                cursor.close();
            }
        }).start();
    }

    /**
     * 将每个图片文件夹下的第1张图片封装成FolderBean对象，添加到list中
     * @return
     */
    public List<FolderBean> getGroupImages(HashMap<String,List<String>> hashMap) {

        if(hashMap.size() == 0){
            return null;
        }
        //遍历hashmap
        List<FolderBean> list = new ArrayList<FolderBean>();
        Iterator<Map.Entry<String,List<String>>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,List<String>> entry = iterator.next();
            String key = entry.getKey();
            List<String> value = entry.getValue();

            FolderBean folderBean = new FolderBean();
            folderBean.setFolderName(key);
            folderBean.setFirstImgPath(value.get(0));
            folderBean.setImgNumber(value.size());

            list.add(folderBean);
        }
        return list;
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Config.SCAN_SUCCUSS :
                    //扫描完毕，关闭进度条
                    mProgressDialog.dismiss();
                    mBeanList = getGroupImages(mHashMap);
                    //设置adapter
                    mListView.setAdapter(new MyGroupImageAdapter(MainActivity.this,mBeanList,Config.options));
            }
        }
    };


}
