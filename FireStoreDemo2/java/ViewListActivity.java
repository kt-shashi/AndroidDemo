package com.shashi.cloudfirestor3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ArrayList<ToDoDataModel> dataList;
    private CustomAdapter myAdapter;

    private FirebaseFirestore firestoredb;
    private CollectionReference collectionReference;
    public static final String COLLECTION_NAME = "todocollection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        initViews();

        setUpRecyclerView();

        getFirestoreData();

    }

    private void initViews() {

        recyclerView = findViewById(R.id.recycler_view_view_activity);
        progressBar = findViewById(R.id.progress_bar_view_activity);
        progressBar.setVisibility(View.VISIBLE);

    }

    private void setUpRecyclerView() {

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        myAdapter = new CustomAdapter(this, dataList);

        recyclerView.setAdapter(myAdapter);
    }

    private void getFirestoreData() {

        firestoredb = FirebaseFirestore.getInstance();
        collectionReference = firestoredb.collection(COLLECTION_NAME);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    ToDoDataModel data = snapshot.toObject(ToDoDataModel.class);
                    data.setDocumentId(snapshot.getId());
                    dataList.add(data);

                    progressBar.setVisibility(View.GONE);
                }

                myAdapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                Log.d("debug_shashi", e.getMessage());
            }
        });
    }

}