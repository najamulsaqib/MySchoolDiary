package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class addWork extends AppCompatActivity {

    private EditText topic, description;
    private Button btn;
    private RadioGroup rg;
    private FirebaseFirestore db;
    private String Topic, Description, ClassCode, Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        --------------------------------------------------->
        topic = findViewById(R.id.topic);
        description = findViewById(R.id.description);
        rg = findViewById(R.id.radio);
        btn = findViewById(R.id.addWork_btn);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");
        Image = "others";
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.science:
                        Image = "science";
                        break;
                    case R.id.english:
                        Image = "english";
                        break;
                    case R.id.urdu:
                        Image = "urdu";
                        break;
                    case R.id.math:
                        Image = "math";
                        break;
                    case R.id.gk:
                        Image = "gk";
                        break;
                    case R.id.other:
                        Image = "others";
                        break;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Topic = topic.getText().toString();
                Description = description.getText().toString();
                if(Topic.isEmpty()){
                    topic.setError("This field cannot be empty!");
                    return;
                }
                if(Description.isEmpty()){
                    description.setError("This field cannot be empty!");
                    return;
                }
                saveToFirebase(ClassCode, Topic, Description, Image);
            }
        });
    }
    public void saveToFirebase(String ClassCode, String Topic, String Description, String Image){
        Date currentTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
        String date = dateOnly.format(cal.getTime());
        System.out.println("Date : "+date);
        String Id = UUID.randomUUID().toString();
        HashMap<String, Object> map = new HashMap<>();
        map.put("ClassCode", ClassCode);
        map.put("Topic", Topic);
        map.put("Description", Description);
        map.put("Image", Image);
        map.put("Date", currentTime);
        map.put("Day", date);
        map.put("Id", Id);

        db.collection("Work").document(Id).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(addWork.this, "Data Saved!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(addWork.this, Teacher.class);
                            i.putExtra("ClassCode", ClassCode);
                            startActivity(i);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addWork.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}