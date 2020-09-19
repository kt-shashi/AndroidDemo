package com.shashi.cloudfirestor3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateListActivity extends AppCompatActivity implements View.OnClickListener {

    EditText titleEditText;
    EditText descriptionEditText;

    Button updateButton;
    Button deleteButton;

    private ToDoDataModel toDoDataModel;        //for document id

    private FirebaseFirestore firestoreDb;
    private CollectionReference collectionReference;
    public static final String COLLECTION_NAME = "todocollection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_list);

        initViews();

        //get Data from Intent
        toDoDataModel = (ToDoDataModel) getIntent().getSerializableExtra("todoitem");
        String title = toDoDataModel.getTitle();
        String desc = toDoDataModel.getDescription();

        //set the data into EditText
        setStoredData(title, desc);

        firestoreDb = FirebaseFirestore.getInstance();
        collectionReference = firestoreDb.collection(COLLECTION_NAME);

    }

    private void initViews() {
        titleEditText = findViewById(R.id.title_edit_text_update_activity);
        descriptionEditText = findViewById(R.id.description_edit_text_update_activity);

        updateButton = findViewById(R.id.save_button_update_activity);
        deleteButton = findViewById(R.id.delete_button_update_activity);

        //set onClick Listners
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    private void setStoredData(String title, String desc) {
        titleEditText.setText(title);
        descriptionEditText.setText(desc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_button_update_activity:
                updateDocument();
                break;
            case R.id.delete_button_update_activity:

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete data");
                builder.setMessage("Are you sure?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.create().dismiss();
                    }
                });

                builder.show();

        }
    }

    private void updateDocument() {

        String titleData = titleEditText.getText().toString().trim();
        String descriptionData = descriptionEditText.getText().toString().trim();

        //Input validation
        if (!checkIfValid(titleData, descriptionData)) {
            return;
        }

        ToDoDataModel dataModel = new ToDoDataModel();
        dataModel.setTitle(titleData);
        dataModel.setDescription(descriptionData);

        //update an entire document (we pass the entire data)
        collectionReference.document(toDoDataModel.getDocumentId()).set(dataModel).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateListActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

        //update an speciofic fields in document (update: we pass keys and respected updated values)
//        collectionReference.document(toDoDataModel.getDocumentId())
//                .update("title", dataModel.getTitle()
//                        , "description", dataModel.getDescription())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(UpdateListActivity.this, "Updated", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(UpdateListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    private void deleteData() {

        collectionReference.document(toDoDataModel.getDocumentId()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateListActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                        finish();

                        Intent intent = new Intent(UpdateListActivity.this, ViewListActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateListActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });

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