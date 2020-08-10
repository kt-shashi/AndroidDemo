package com.shashi.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    List<ProductsData> productsDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        //Kis type se list banana hai
        //LinearLayout me vertical list banta hai
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Getting data
        productsDataList = new ArrayList<>();
        setProducts();

        productsAdapter = new ProductsAdapter(this, productsDataList);
        //attach adapter to recycler view
        recyclerView.setAdapter(productsAdapter);
    }

    private void setProducts() {

        productsDataList.add(new ProductsData(
                R.drawable.apple, "Apple", 50
        ));


        productsDataList.add(new ProductsData(
                R.drawable.banana, "Banana", 30
        ));

        productsDataList.add(new ProductsData(
                R.drawable.lemon, "Lemon", 8
        ));

        productsDataList.add(new ProductsData(
                R.drawable.mango, "Mango", 55
        ));

        productsDataList.add(new ProductsData(
                R.drawable.pineapple, "Pineapple", 56
        ));

        productsDataList.add(new ProductsData(
                R.drawable.strawberry, "Strawberry", 45
        ));

        productsDataList.add(new ProductsData(
                R.drawable.watermelon, "Watermelon", 80
        ));
    }

}