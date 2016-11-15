package com.meizu.scanpictures.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.meizu.hmh.view.R;
import com.meizu.scanpictures.bean.FolderBean;
import com.meizu.scanpictures.config.Config;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by hmh on 2015/11/4.
 */
public class MyChildImageAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mChildList;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;
    public MyChildImageAdapter(Context context, List<String> list, DisplayImageOptions options){

        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mChildList = list;
        this.mOptions = options;
    }
    @Override
    public int getCount() {
        return mChildList.size();
    }

    @Override
    public Object getItem(int position) {
        return mChildList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.gridview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.childImageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(mChildList.get(position),viewHolder.childImageView, Config.options);
        viewHolder.childImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,mChildList.get(position),Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        private ImageView childImageView;
    }
}
