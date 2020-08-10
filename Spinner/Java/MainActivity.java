package com.shashi.spinnerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormatSymbols;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinnerMonth, spinnerYear;
    Button buttonSubmit;
    String[] months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMonth = findViewById(R.id.spinner_month);
        spinnerYear = findViewById(R.id.spinner_year);
        buttonSubmit = findViewById(R.id.button_submit);

        populateSpinnerMonth();
        populateSpinnerYear();

        buttonSubmit.setOnClickListener(this);
        spinnerMonth.setOnItemSelectedListener(this);
        spinnerYear.setOnItemSelectedListener(this);
    }

    private void populateSpinnerMonth() {
        months = new DateFormatSymbols().getMonths();

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(monthAdapter);
    }

    private void populateSpinnerYear() {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>

                (this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_year));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);
    }

    //Button click listner
    @Override
    public void onClick(View view) {
        String monthSelected = spinnerMonth.getSelectedItem().toString();
        String yearSelected = spinnerYear.getSelectedItem().toString();

        Toast.makeText(MainActivity.this,
                monthSelected + " + " + yearSelected, Toast.LENGTH_SHORT).show();
    }

    //Item select listner on spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinner_month) {

//            String selectedMonth = adapterView.getItemAtPosition(i).toString();
            String selectedMonth = adapterView.getSelectedItem().toString();
            Toast.makeText(this, selectedMonth, Toast.LENGTH_SHORT).show();

        } else if (adapterView.getId() == R.id.spinner_year) {

            String selectedYear = adapterView.getSelectedItem().toString();
            Toast.makeText(this, selectedYear, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}