package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class AddNote extends AppCompatActivity {

    private String ClassCode, Name, Subject, Query;
    private EditText subject, query;
    private FirebaseFirestore db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//      ------------------------------------------------------->
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");
        Name = intent.getStringExtra("Name");
        subject = findViewById(R.id.subject);
        query = findViewById(R.id.query);
        btn = findViewById(R.id.addWork_btn);
        db = FirebaseFirestore.getInstance();
//      ------------------------------------------------------->
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject = subject.getText().toString();
                Query = query.getText().toString();
                if(Subject.isEmpty()){
                    subject.setError("This field Cannot be Empty!");
                    return;
                }
                if(Query.isEmpty()){
                    query.setError("This field Cannot be Empty!");
                    return;
                }
                saveToFirebase(ClassCode, Name, Subject, Query);
            }
        });

    }
    public void saveToFirebase(String ClassCode, String Name, String Subject, String Query){
        String Id = UUID.randomUUID().toString();
        HashMap<String, Object> map = new HashMap<>();
        map.put("ClassCode", ClassCode);
        map.put("Name", Name);
        map.put("Subject", Subject);
        map.put("Query", Query);
        map.put("Id", Id);
        map.put("teacher", false);

        db.collection("Note").document(Id).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddNote.this, "Data Saved!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AddNote.this, Student.class);
                            i.putExtra("ClassCode", ClassCode);
                            startActivity(i);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNote.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}