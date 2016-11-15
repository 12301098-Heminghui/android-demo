package com.meizu.scanpictures.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meizu.hmh.view.R;
import com.meizu.scanpictures.bean.FolderBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by hmh on 2015/11/2.
 */
public class MyGroupImageAdapter extends BaseAdapter{

    private LayoutInflater mLayoutInflater;
    private List<FolderBean> mBeanList;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;

    public MyGroupImageAdapter(Context context, List<FolderBean> list, DisplayImageOptions options){

        this.mLayoutInflater = LayoutInflater.from(context);
        this.mBeanList = list;
        this.mOptions = options;
    }

    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public Object getItem(int position) {

        return mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.folderName_tv = (TextView) convertView.findViewById(R.id.folderName);
            viewHolder.picNumber_tv = (TextView) convertView.findViewById(R.id.picNumber);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.folderName_tv.setText(mBeanList.get(position).getFolderName());
        System.out.println("文件夹下图片数量：" + mBeanList.get(position).getImgNumber());
        viewHolder.picNumber_tv.setText(String.valueOf(mBeanList.get(position).getImgNumber()));

        imageLoader.displayImage(mBeanList.get(position).getFirstImgPath(),viewHolder.imageView,mOptions);
        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView folderName_tv;
        private TextView picNumber_tv;
    }
}
