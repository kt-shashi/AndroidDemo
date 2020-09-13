package com.shashi.firestoredemo1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleEditText, thoughtEditText;
    private Button saveButton, showButton, updateButton, deleteButton;
    private TextView resultTitleTextView, resultThoughTextView;

    //KEYS
    public static final String KEY_TITLE = "title";
    public static final String KEY_THOUGHT = "thought";

    //create connection to Firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference = db.collection("Journal").document("First Though");
    private CollectionReference collectionReference = db.collection("Journal");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        titleEditText = findViewById(R.id.edit_text_title);
        thoughtEditText = findViewById(R.id.edit_text_thoughts);

        saveButton = findViewById(R.id.save_button);
        showButton = findViewById(R.id.show_button);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);

        resultTitleTextView = findViewById(R.id.result_title_textView);
        resultThoughTextView = findViewById(R.id.result_thought_textView);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.save_button:
//                addData();
                addMultipleData();
                break;
            case R.id.show_button:
//                readData();
                readAllData();
                break;
            case R.id.update_button:
                updateData();
                break;
            case R.id.delete_button:
                deleteData();
                break;
        }

    }

    private boolean validateDataFromUser(String titleString, String thoughtsString) {
        if (titleString.isEmpty()) {
            titleEditText.setError("Cannot be Empty!");
            return false;
        } else if (thoughtsString.isEmpty()) {
            thoughtEditText.setError("Cannot be Empty!");
            return false;
        } else return true;
    }

    private void addData() {

        //Get data from user and validate
        String titleString = titleEditText.getText().toString().trim();
        String thoughtsString = thoughtEditText.getText().toString().trim();
        if (!validateDataFromUser(titleString, thoughtsString)) {
            return;
        }

        //Firestore stores data in Key Value pair

        //Using Model class
        JournalModelClass journalData = new JournalModelClass();
        journalData.setTitle(titleString);
        journalData.setThought(thoughtsString);

        //Using hashmap
//        Map<String, Object> data = new HashMap<>();
//        data.put(KEY_TITLE, titleString);
//        data.put(KEY_THOUGHT, thoughtsString);

        db.collection("Journal").document("First Though")
                .set(journalData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addMultipleData() {

        //Get data from user and validate
        String titleString = titleEditText.getText().toString().trim();
        String thoughtsString = thoughtEditText.getText().toString().trim();
        if (!validateDataFromUser(titleString, thoughtsString)) {
            return;
        }

        JournalModelClass journalData = new JournalModelClass();
        journalData.setTitle(titleString);
        journalData.setThought(thoughtsString);

        collectionReference.add(journalData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void readData() {

        db.collection("Journal").document("First Though")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        //Check if the document exists
                        if (documentSnapshot.exists()) {

                            //Using Model Class
                            JournalModelClass journalData = documentSnapshot.toObject(JournalModelClass.class);
                            String title = journalData.getTitle();
                            String thought = journalData.getThought();
                            if (journalData != null) {
                                resultTitleTextView.setText(title);
                                resultThoughTextView.setText(thought);
                            }

                            //Using HashMap
//                            String title = documentSnapshot.getString(KEY_TITLE);
//                            String thought = documentSnapshot.getString(KEY_THOUGHT);

                        } else {
                            Toast.makeText(MainActivity.this, "No data exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void readAllData() {

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Log.d("shashi_debug", "ID: " + snapshot.getId());
                    JournalModelClass journal = snapshot.toObject(JournalModelClass.class);
                }

                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Log.d("shashi_debug", "ID: " + snapshot.getString(KEY_TITLE));
                    Log.d("shashi_debug", "ID: " + snapshot.getString(KEY_THOUGHT));
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    private void updateData() {
        String titleString = titleEditText.getText().toString().trim();
        String thoughtsString = thoughtEditText.getText().toString().trim();

        if (!validateDataFromUser(titleString, thoughtsString)) {
            return;
        }

        //Put the data into MAP
        Map<String, Object> data = new HashMap<>();
        data.put(KEY_TITLE, titleString);
        data.put(KEY_THOUGHT, thoughtsString);

        documentReference.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData() {

//        //delete document
//        documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });

        //delete fields
        Map<String, Object> delete_data = new HashMap<>();
        delete_data.put(KEY_TITLE, FieldValue.delete());
        delete_data.put(KEY_THOUGHT, FieldValue.delete());

        documentReference.update(delete_data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

//        //If there is any change in the data, snapshot listner is called
//
//        //We can either stop the listner in onStop, or we can pass Activity to destroy it once we are outside
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//
//                if (e != null) {
//                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //if data is present, display it
//                if (documentSnapshot != null && documentSnapshot.exists()) {
//
//                    //Using Model class
//                    JournalModelClass journalData = documentSnapshot.toObject(JournalModelClass.class);
//                    String title = journalData.getTitle();
//                    String thought = journalData.getThought();
//                    if (journalData != null) {
//                        resultTitleTextView.setText(title);
//                        resultThoughTextView.setText(thought);
//                    }
//
//                    //Using Hashmap
////                    String title = documentSnapshot.getString(KEY_TITLE);
////                    String thought = documentSnapshot.getString(KEY_THOUGHT);
////
////                    resultTitleTextView.setText(title);
////                    resultThoughTextView.setText(thought);
//                }
//                //If there is no data, maybe the user deleted it, so we have to clear the previous data shown
//                else {
//                    resultTitleTextView.setText("");
//                    resultThoughTextView.setText("");
//                }
//            }
//        });


        //For multiple data
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Log.d("shashi_debug", "ID: " + snapshot.getId());
                    JournalModelClass journal = snapshot.toObject(JournalModelClass.class);
                    resultTitleTextView.append("Title: " + journal.getTitle() + "\n");
                    resultThoughTextView.append("Thought: " + journal.getThought() + "\n");
                }

                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Log.d("shashi_debug", "ID: " + snapshot.getString(KEY_TITLE));
                    Log.d("shashi_debug", "ID: " + snapshot.getString(KEY_THOUGHT));
                }

            }
        });

    }


}