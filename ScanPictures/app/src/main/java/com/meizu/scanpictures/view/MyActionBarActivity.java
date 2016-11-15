package com.meizu.scanpictures.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.meizu.hmh.view.R;

/**
 * Created by hmh on 2015/11/4.
 */
public class MyActionBarActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
        ActionBar actionBar = getActionBar();
        /*//actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);*/
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }

    }
}
