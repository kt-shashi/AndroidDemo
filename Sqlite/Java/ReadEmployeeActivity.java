package com.shashi.employeemanagementsql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    MyDbHandler myDbHandler;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_employee);

        initViews();

        myDbHandler = TempDataBaseHandler.getMyDbHandler();
        ArrayList<EmployeeDataModel> employeeList = myDbHandler.readEmployeeData();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReadAdapter(employeeList, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view_read);
        goBack = findViewById(R.id.go_back_read);

        goBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}