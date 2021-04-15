package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class teacherDashboard extends AppCompatActivity {

    private ImageView Attendance, Diary, Timetable, Logout;
    private TextView headerName, CodeView;
    private DatabaseReference reference;
    private String UserID, ClassCode, Name;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        getSupportActionBar().hide();
//        --------------------------------------------------------------------->
        Attendance = findViewById(R.id.bus);
        Diary = findViewById(R.id.imageView3);
        headerName = findViewById(R.id.textView);
        Timetable = findViewById(R.id.bus1);
        Logout = findViewById(R.id.imageView31);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UserID = user.getUid();
        CodeView = findViewById(R.id.textView2);

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userGet = snapshot.getValue(Users.class);
                if(userGet != null){
                    ClassCode = userGet.Code;
                    Name = userGet.Name;
                    headerName.setText(Name);
                    CodeView.setText("Your Class code : '"+ClassCode+"'.");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(teacherDashboard.this, "Something Happened Wrong!", Toast.LENGTH_LONG).show();
            }
        });

        Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(teacherDashboard.this, Teacher.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                startActivity(i);
            }
        });

        Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(teacherDashboard.this, Teacher.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                startActivity(i);
            }
        });
        Timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(teacherDashboard.this, TimeTable.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                startActivity(i);            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(teacherDashboard.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}