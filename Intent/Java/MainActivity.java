package com.shashi.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text_username);
        button = findViewById(R.id.go_to_second_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String str = editText.getText().toString().trim();
        if (str.isEmpty()) {
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity2.class);
        //current activity, to activity
        // .class kyuki run time pe java bytecode me convert hoga
        // matlab .java file .class bann jaega
        //usko run time pe call karne ke liye .class

        //Key value pair
        intent.putExtra("username", str);
        intent.putExtra("index", 1001);

        startActivity(intent);      //to start the activity

//        finish();   //Cannot go back to this activity
    }
}