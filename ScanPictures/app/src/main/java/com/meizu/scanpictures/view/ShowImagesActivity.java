package com.meizu.scanpictures.view;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.meizu.hmh.view.R;
import com.meizu.scanpictures.config.Config;

import java.util.List;

public class ShowImagesActivity extends AppCompatActivity {

    private GridView mGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        getSupportActionBar().setTitle(R.string.showImageActivity_tip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mGridView = (GridView) findViewById(R.id.gridView);
        Intent intent = getIntent();
        List<String> childList = intent.getStringArrayListExtra("childList");
        mGridView.setAdapter(new MyChildImageAdapter(this,childList, Config.options));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                System.out.println("返回键结束当前activity ");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
