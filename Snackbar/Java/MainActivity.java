package com.shashi.snackbardemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //add dependency: implementation 'com.android.support:design:28.0.0'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ConstraintLayout constraintLayout = findViewById(R.id.parent_layout);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //view (find a parent), text, duration
                Snackbar snackbar = Snackbar.make(constraintLayout, "Demo Snackbar", Snackbar.LENGTH_LONG);
                snackbar.show();

                //Customise Snackbar
                snackbar.setAction("Click Me", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "SnackBar clicked!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

}