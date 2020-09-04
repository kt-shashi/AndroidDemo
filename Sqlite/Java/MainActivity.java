package com.shashi.employeemanagementsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String DATABASE_NAME = "employeedb";

    ImageButton createButtonClicked;
    ImageButton readButtonClicked;
    ImageButton updateButtonClicked;
    ImageButton deleteButtonClicked;

    Button developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        MyDbHandler dbHandler = new MyDbHandler(getApplicationContext(), DATABASE_NAME, null, 1);
        TempDataBaseHandler.setMyDbHandler(dbHandler);

    }

    public void initViews() {
        createButtonClicked = findViewById(R.id.create_button_main);
        readButtonClicked = findViewById(R.id.read_button_main);
        updateButtonClicked = findViewById(R.id.update_button_main);
        deleteButtonClicked = findViewById(R.id.delete_button_main);

        //Set onClick Listner
        createButtonClicked.setOnClickListener(this);
        readButtonClicked.setOnClickListener(this);
        updateButtonClicked.setOnClickListener(this);
        deleteButtonClicked.setOnClickListener(this);

        developer=findViewById(R.id.developer_contact_main);
        developer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_button_main:
                createEmployeeClicked();
                break;
            case R.id.read_button_main:
                readEmployeeClicked();
                break;
            case R.id.update_button_main:
                updateEmployeeClicked();
                break;
            case R.id.delete_button_main:
                deleteEmployeeClicked();
                break;
            case R.id.developer_contact_main:
                contactDeveloper();
                break;
        }
    }

    //Open new Activity when clicked
    private void createEmployeeClicked() {
        Intent intent = new Intent(getApplicationContext(), CreateEmployeeActivity.class);
        startActivity(intent);
    }

    private void readEmployeeClicked() {
        Intent intent = new Intent(getApplicationContext(), ReadEmployeeActivity.class);
        startActivity(intent);
    }

    private void updateEmployeeClicked() {
        Intent intent = new Intent(getApplicationContext(), UpdateEmployeeActivity.class);
        startActivity(intent);
    }

    private void deleteEmployeeClicked() {
        Intent intent = new Intent(getApplicationContext(), DeleteEmployeeActivity.class);
        startActivity(intent);
    }

    private void contactDeveloper(){
        Toast.makeText(this, "Developer: SHASHI", Toast.LENGTH_LONG).show();
    }

}