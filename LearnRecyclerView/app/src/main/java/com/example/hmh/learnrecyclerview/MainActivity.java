package com.example.hmh.learnrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Externalizable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mRecyclerView = new RecyclerView(this);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            //自定义创建类
            class MyViewHolder extends RecyclerView.ViewHolder {
                private TextView tv;//子视图

                public MyViewHolder(View itemView) {
                    super(itemView);
                   // tv = (TextView) itemView;
                }

              /*  public TextView getTv() {
                    return this.tv;
                }
*/
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
                View v = mLayoutInflater.inflate(R.layout.item_recyclerview,parent,false);

              /*  View v = mLayoutInflater.inflate(R.layout.item_recyclerview, null);
                TextView tv = (TextView) v.findViewById(R.id.textview);*/
                MyViewHolder viewHolder = new MyViewHolder(v);
                viewHolder.tv = (TextView)v.findViewById(R.id.textview);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                MyViewHolder viewHolder = (MyViewHolder)holder;
                viewHolder.tv.setText("Item" + position);
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }

}
