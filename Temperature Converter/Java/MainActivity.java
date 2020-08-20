package com.shashi.tempconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText temperatureEditText;
    Spinner temperatureSpinner;

    Button convertButton;
    Button clearButton;

    TextView celsiusTextView;
    TextView fahrenheitTextView;
    TextView kelvinTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        convertButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

    }

    private void initViews() {

        temperatureEditText = findViewById(R.id.temp_edit_text);
        temperatureSpinner = findViewById(R.id.temp_spinner);

        convertButton = findViewById(R.id.convert_button);
        clearButton = findViewById(R.id.clear_button);

        celsiusTextView = findViewById(R.id.celsius_text_view);
        fahrenheitTextView = findViewById(R.id.fahrenheit_text_view);
        kelvinTextView = findViewById(R.id.kelvin_text_view);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.convert_button:
                convertHelper();
                break;
            case R.id.clear_button:
                clear();
                break;
        }

    }

    private void convertHelper() {

        if (temperatureEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Field cannot be EMPTY!", Toast.LENGTH_SHORT).show();
            return;
        }

        String tempSelected = temperatureSpinner.getSelectedItem().toString();

        if (tempSelected.compareTo("Celsius") == 0)
            convertCelsius();
        if (tempSelected.compareTo("Fahrenheit") == 0)
            convertFarenheit();
        if (tempSelected.compareTo("Kelvin") == 0)
            convertKelvin();

    }

    private void convertCelsius() {

        String celsiusString = temperatureEditText.getText().toString();
        double celsius = Double.parseDouble(celsiusString);

        double fahrenheit = (celsius * (9.0 / 5.0)) + 32;
        double kelvin = (celsius + 273.15);

        showResult(celsius, fahrenheit, kelvin);

    }

    private void convertFarenheit() {

        String fahrenheitString = temperatureEditText.getText().toString();
        double fahrenheit = Double.parseDouble(fahrenheitString);

        double celsius = ((fahrenheit - 32) * 5) / 9;
        double kelvin = (celsius + 273.15);

        showResult(celsius, fahrenheit, kelvin);
    }

    private void convertKelvin() {

        String kelvinString = temperatureEditText.getText().toString();
        double kelvin = Double.parseDouble(kelvinString);

        double celsius = kelvin - 273.15;
        double fahrenheit = ((celsius * 9) / 5) + 32;

        showResult(celsius, fahrenheit, kelvin);

    }

    private void showResult(double celsius, double fahrenheit, double kelvin) {

        celsiusTextView.setText("Celsius: " + celsius + " °C");
        fahrenheitTextView.setText("Fahrenheit: " + fahrenheit + " °F");
        kelvinTextView.setText("Kelvin: " + kelvin + " K");

    }

    private void clear() {

        celsiusTextView.setText("");
        fahrenheitTextView.setText("");
        kelvinTextView.setText("");

        temperatureEditText.setText("");

        temperatureEditText.requestFocus();

    }

}