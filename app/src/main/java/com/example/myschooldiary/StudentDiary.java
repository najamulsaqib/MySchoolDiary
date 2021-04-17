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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentDiary extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<model> dataList;
    private RecyclerView recyclerView;
    private customAdapter adapter;
    private String ClassCode;
    private ImageView nothing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_diary);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        --------------------------------------------------->
        recyclerView = findViewById(R.id.recyclerView);
        nothing = findViewById(R.id.nothing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseFirestore = FirebaseFirestore.getInstance();
        dataList = new ArrayList<>();
        adapter = new customAdapter(dataList, this);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
        String date = dateOnly.format(cal.getTime());
        Object Date = date;
        Object classCode = ClassCode;
        firebaseFirestore.collection("Work")
                .whereEqualTo("ClassCode", classCode)
                .whereEqualTo("Day", Date)
                .orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        System.out.println(ClassCode);
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot row:list){
                            model obj = row.toObject(model.class);
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