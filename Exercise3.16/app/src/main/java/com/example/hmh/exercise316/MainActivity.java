package com.example.hmh.exercise316;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView shoppingList = (ListView)findViewById(R.id.listView);
        ArrayList<String> listData = new ArrayList<String>();
        listData.add("Apple");
        listData.add("Tomatoes");
        listData.add("Noodles");
        listData.add("Pumpkin");

      /*  ArrayAdapter<String> listAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_expandable_list_item_1,listData);*/
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>
                (this,R.layout.shopping_item,R.id.itemText,listData);
        shoppingList.setAdapter(listAdapter);

        shoppingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkbox = (CheckBox)findViewById(R.id.checkBox);
                if(!checkbox.isChecked()){
                checkbox.setChecked(false);
                view.setBackgroundColor(Color.GREEN);
                }else {
                    checkbox.setChecked(true);
                    view.setBackgroundColor(Color.BLUE);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
