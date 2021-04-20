package com.example.myschooldiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
    private ImageView nothing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_query_teacher_side);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        ---------------------------------------------------------->
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        nothing = findViewById(R.id.nothing);
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
                .whereEqualTo("teacher", false  )
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
                        if(dataList.size() < 1){
                            recyclerView.setVisibility(View.GONE);
                            nothing.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}