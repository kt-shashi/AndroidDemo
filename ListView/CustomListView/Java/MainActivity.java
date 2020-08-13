package com.shashi.listviewdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ModelClassForData> fruitDataList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        //initializing data
        settingUpData();

        MyCustomListAdapter adapter = new MyCustomListAdapter(this, R.layout.my_list_item, fruitDataList);
        listView.setAdapter(adapter);
    }

    private void settingUpData() {
        fruitDataList = new ArrayList<>();

        fruitDataList.add(new ModelClassForData(R.drawable.apple, "Apple", "Noice"));
        fruitDataList.add(new ModelClassForData(R.drawable.banana, "Banana", "Noice"));
        fruitDataList.add(new ModelClassForData(R.drawable.lemon, "Lemon", "Bed"));
        fruitDataList.add(new ModelClassForData(R.drawable.mango, "Mango", "Noice"));
        fruitDataList.add(new ModelClassForData(R.drawable.pineapple, "Pineapple", "Bad"));
        fruitDataList.add(new ModelClassForData(R.drawable.strawberry, "Strawberry", "Noice"));
        fruitDataList.add(new ModelClassForData(R.drawable.watermelon, "Watermelon", "Noice"));
    }
}