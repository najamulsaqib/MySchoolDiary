package com.example.myschooldiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TimeTableStudent extends AppCompatActivity {
    TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    String ClassCode, UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_student);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        ------------------------------------------------------->
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");
        UserID = intent.getStringExtra("User");

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Monday");
                startActivity(i);
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Tuesday");
                startActivity(i);
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Wednesday");
                startActivity(i);
            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Thursday");
                startActivity(i);
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Friday");
                startActivity(i);
            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Saturday");
                startActivity(i);
            }
        });
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TimeTableStudent.this, popup.class);
                i.putExtra("ClassCode", ClassCode);
                i.putExtra("User", UserID);
                i.putExtra("Day", "Sunday");
                startActivity(i);
            }
        });
    }
}