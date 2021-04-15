package com.example.myschooldiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ParentsQueryTeacherSide extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private parentContactAdapter adapter;
    private ArrayList<QueryModel> dataList;
    private String ClassCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_query_teacher_side);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        ---------------------------------------------------------->
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new parentContactAdapter(dataList, this);
        recyclerView.setAdapter(adapter);
//        ---------------------------------------------------------->
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");

        Object classCode = ClassCode;
        firebaseFirestore.collection("Note")
                .whereEqualTo("ClassCode", classCode)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        System.out.println(ClassCode);
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot row:list){
                            QueryModel obj = row.toObject(QueryModel.class);
                            dataList.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}