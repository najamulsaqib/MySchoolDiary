package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student extends AppCompatActivity {
    private FirebaseUser user;
    private ImageView Notes, Diary, Timetable, Bus;
    private TextView studentName;
    private DatabaseReference reference;
    String Name, ClassCode, UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_student);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        --------------------------------------------------->
        NavigationView navigationView = findViewById(R.id.nav_view);
        studentName = findViewById(R.id.textView);
        Notes = findViewById(R.id.bus);
        Diary = findViewById(R.id.imageView3);
        Timetable = findViewById(R.id.bus1);
        Bus = findViewById(R.id.imageView31);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        UserID = user.getUid();
        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userGet = snapshot.getValue(Users.class);
                if(userGet != null){
                    ClassCode = userGet.Code;
                    Name = userGet.Name;
                    studentName.setText(Name);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Student.this, "Something Happened Wrong!", Toast.LENGTH_LONG).show();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_notes){
                    Intent i = new Intent(Student.this, AddNote.class);
                    i.putExtra("ClassCode", ClassCode);
                    i.putExtra("Name", Name);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_diary){
                    Intent i = new Intent(Student.this, StudentDiary.class);
                    i.putExtra("ClassCode", ClassCode);
                    i.putExtra("Name", Name);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_time_table){
                    Intent i = new Intent(Student.this, TimeTableStudent.class);
                    i.putExtra("ClassCode", ClassCode);
                    i.putExtra("Name", Name);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_bus){
                    Intent i = new Intent(Student.this, TrackBus.class);
                    i.putExtra("ClassCode", ClassCode);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_logout){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(Student.this, Login.class));
                    finish();
                }
                return true;
            }
        });
        Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this, AddNote.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("Name", Name);
                startActivity(i);
            }
        });
        Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this, StudentDiary.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("Name", Name);
                startActivity(i);
            }
        });
        Timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this, TimeTableStudent.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("Name", Name);
                startActivity(i);
            }
        });
        Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this, TrackBus.class);
                i.putExtra("ClassCode", ClassCode);
                startActivity(i);
            }
        });
    }
}