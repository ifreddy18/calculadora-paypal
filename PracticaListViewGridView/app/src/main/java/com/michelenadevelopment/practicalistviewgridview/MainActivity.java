package com.michelenadevelopment.practicalistviewgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        name = new ArrayList<>();
        name.add("Name1");
        name.add("Name2");
        name.add("Name3");
        name.add("Name4");
        name.add("Name5");
        name.add("Name6");
        name.add("Name7");
        name.add("Name8");
        name.add("Name9");
        name.add("Name10");


        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);

        MyAdapter myAdapter = new MyAdapter(this,R.layout.listview_layout, name);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Positon: " + name.get(position),
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}
