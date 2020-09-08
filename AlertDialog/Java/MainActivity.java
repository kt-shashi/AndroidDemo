package com.shashi.alertdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonDefault;
    private Button buttonCustom;

    private TextView nameTextView;
    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        buttonDefault = findViewById(R.id.buttonDefault);
        buttonCustom = findViewById(R.id.button_custom);

        nameTextView = findViewById(R.id.name_text_view);
        passwordTextView = findViewById(R.id.password_text_view);

        buttonDefault.setOnClickListener(this);
        buttonCustom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDefault:
                defaultDialog();
                break;
            case R.id.button_custom:
                customDialog();
                break;
        }
    }

    public void defaultDialog() {

        //Create Builder object
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //set Title Message and Icon
        builder.setTitle("Age Selection!");
        builder.setMessage("Are you 18+ ?");
        builder.setIcon(R.drawable.ic_launcher_background);

        builder.setCancelable(false);           //user cannot cancel the dialog, must take an action

        //Set Buttons for Dialog
        builder.setPositiveButton("Yes, 18+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "18+ selected", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No <18", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "<18 Selected", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.create().dismiss();             //To explicitly dismiss the dialog
            }
        });

        builder.show();

    }

    private void customDialog() {

        //We get data from layout_dialog.xml and set data to activity_main.xml

        //Inflate the view
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dailog, null);

        final EditText usernameEditText = view.findViewById(R.id.name_edit_text);
        final EditText passwordEditText = view.findViewById(R.id.password_edit_text);

        //Create Builder object
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //set Title Message and Icon
        builder.setTitle("Login");
        builder.setMessage("Enter details");
        builder.setView(view);

        builder.setCancelable(false);    //user cannot cancel the dialog, must take an action

        //Set Buttons for Dialog
        
        //Method 1:
        
//         builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialogInterface, int i) {

//                 String name = usernameEditText.getText().toString().trim();
//                 String password = passwordEditText.getText().toString().trim();

//                 if (name.isEmpty() || password.isEmpty()) {
//                     Toast.makeText(MainActivity.this, "Cannot be Empty", Toast.LENGTH_SHORT).show();
//                     builder.create().dismiss();
//                 }

//                 nameTextView.setText(name);
//                 passwordTextView.setText(password);
//             }
//         });
//         builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialogInterface, int i) {
//                 builder.create().dismiss();
//             }
//         });
//       builder.show();        
        
        //Method 2:
        //We get data from layout_dialog.xml and set data to activity_main.xml

        //Inflate the view
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dailog, null);

        final EditText usernameEditText = view.findViewById(R.id.name_edit_text);
        final EditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final Button saveButton = view.findViewById(R.id.buttonSave);

        //Create Builder object
        builder = new AlertDialog.Builder(this);
 
        //set Title Message and Icon
        builder.setTitle("Login");
        builder.setMessage("Enter details");
        builder.setView(view);

        builder.setCancelable(false);    //user cannot cancel the dialog, must take an action

        final AlertDialog alertDialog = builder.create();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Cannot be Empty", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }

                nameTextView.setText(name);
                passwordTextView.setText(password);

                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }
}
