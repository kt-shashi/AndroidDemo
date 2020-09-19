package com.shashi.cloudfirestor3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText titleEditText;
    EditText descriptionEditText;

    Button saveButton;
    Button viewButton;

    private FirebaseFirestore firestoreDb;
    private CollectionReference collectionReference;
    public static final String COLLECTION_NAME = "todocollection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        firestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firestoreDb.collection(COLLECTION_NAME);

    }

    private void initViews() {
        titleEditText = findViewById(R.id.title_edit_text_main_activity);
        descriptionEditText = findViewById(R.id.description_edit_text_main_activity);

        saveButton = findViewById(R.id.save_button_main_activity);
        viewButton = findViewById(R.id.view_button_main_activity);

        //set onClick Listners
        saveButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_button_main_activity:
                saveData();
                break;
            case R.id.view_button_main_activity:
                viewData();
                break;
        }
    }

    private void saveData() {

        String titleData = titleEditText.getText().toString().trim();
        String descriptionData = descriptionEditText.getText().toString().trim();

        //Input validation
        if (!checkIfValid(titleData, descriptionData)) {
            return;
        }

        ToDoDataModel dataModel = new ToDoDataModel();
        dataModel.setTitle(titleData);
        dataModel.setDescription(descriptionData);

        collectionReference.add(dataModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                clearDataFromViews();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                Log.d("debug_shashi", e.getMessage());
            }
        });

    }

    private void viewData() {
        Intent intent = new Intent(MainActivity.this, ViewListActivity.class);
        startActivity(intent);
    }

    private void clearDataFromViews() {
        titleEditText.setText("");
        descriptionEditText.setText("");
    }

    //Input Validation
    private boolean checkIfValid(String titleData, String descriptionData) {

        if (titleData.isEmpty()) {
            titleEditText.setError("This field cannot be blank");
            return false;
        }

        if (descriptionData.isEmpty()) {
            descriptionEditText.setError("This field cannot be blank");
            return false;
        }

        return true;
    }
}