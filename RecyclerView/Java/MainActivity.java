package com.shashi.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<DataModel> dataModelList = new ArrayList<>();
        dataModelList = setData(dataModelList);

        //Set up Recycler view
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView2 = findViewById(R.id.recycler2_view_main);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation

        MyAdapter myAdapter = new MyAdapter(this, dataModelList);

        recyclerView.setLayoutManager(linearLayoutManager1);
        recyclerView.setAdapter(myAdapter);

        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(myAdapter);

    }

    private ArrayList setData(ArrayList<DataModel> arrayList) {
        for (int i = 10; i < 20; i++) {
            DataModel data = new DataModel();
            data.setName("Name" + i);
            data.setPhone("Phone" + i);

            arrayList.add(data);
        }
        return arrayList;
    }
}