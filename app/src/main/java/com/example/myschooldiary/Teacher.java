package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Teacher extends AppCompatActivity {

    private FirebaseUser user;

    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private String UserID, ClassCode, Name;
    private TextView headerName, noWork;
    private ArrayList<model> dataList;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        getSupportActionBar().hide();
//        --------------------------------------------------->
        headerName = findViewById(R.id.headerName);
        noWork = findViewById(R.id.noWork);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UserID = user.getUid();
//        ---------------------------------------------------->
        dataList = new ArrayList<>();
        adapter = new myAdapter(dataList, this);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");
        UserID = intent.getStringExtra("User");
//        ------------------------------------------------------>

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Teacher.this, addWork.class);
                i.putExtra("ClassCode", ClassCode);
                startActivity(i);
            }
        });
//        ---------------------------------------------------->
        Object classCode = ClassCode;
        firebaseFirestore.collection("Work")
                .whereEqualTo("ClassCode", classCode)
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
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}