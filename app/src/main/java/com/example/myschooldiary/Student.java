package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String UserID, ClassCode, Name;
    private TextView headerName, noWork;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<model> dataList;
    private RecyclerView recyclerView;
    private myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getSupportActionBar().hide();
//        --------------------------------------------------->
        headerName = findViewById(R.id.headerName);
        noWork = findViewById(R.id.noWork);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        UserID = user.getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        //        ---------------------------------------------------->
        dataList = new ArrayList<>();
//        adapter = new myAdapter(dataList);
        recyclerView.setAdapter(adapter);
        //        ---------------------------------------------------->
        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userGet = snapshot.getValue(Users.class);
                if(userGet != null){
                    ClassCode = userGet.Code;
                    Name = userGet.Name;
                    headerName.setText("Hi! "+Name);
                }else {
//                    noWork.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Student.this, "Something Happened Wrong!", Toast.LENGTH_LONG).show();
            }
        });
//------------------------------------------------------->
        firebaseFirestore.collection("Work")
                .whereEqualTo("ClassCode", "SZR4OP")
                .orderBy("Date")
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
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}