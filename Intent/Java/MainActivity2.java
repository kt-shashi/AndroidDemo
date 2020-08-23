package com.shashi.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        textView = findViewById(R.id.text_view_get_username);

        //returns key corresponding to "username" value
        String str = getIntent().getStringExtra("username");
        int index = getIntent().getIntExtra("index", 0000);

        textView.setText(str + index);


    }
}