package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
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
import com.google.protobuf.DescriptorProtos;

import java.util.ArrayList;
import java.util.List;

public class teacherDashboard extends AppCompatActivity {

    private ImageView Notes, Diary, Timetable, Bus;
    private TextView headerName, CodeView;
    private DatabaseReference reference;
    private String UserID, ClassCode, Name;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_teacher);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        --------------------------------------------------------------------->
        NavigationView navigationView = findViewById(R.id.nav_view);
        Notes = findViewById(R.id.bus);
        Diary = findViewById(R.id.imageView3);
        headerName = findViewById(R.id.textView);
        Timetable = findViewById(R.id.bus1);
        Bus = findViewById(R.id.imageView31);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UserID = user.getUid();
        CodeView = findViewById(R.id.textView2);
        CodeView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", ClassCode);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(teacherDashboard.this, "Class Code Copied", Toast.LENGTH_LONG).show();
            }
        });
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_notes){
                    Intent i = new Intent(teacherDashboard.this, ParentsQueryTeacherSide.class);
                    i.putExtra("ClassCode", ClassCode);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_diary){
                    Intent i = new Intent(teacherDashboard.this, Teacher.class);
                    i.putExtra("ClassCode", ClassCode);
                    i.putExtra("User", UserID);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_time_table){
                    Intent i = new Intent(teacherDashboard.this, TimeTable.class);
                    i.putExtra("ClassCode", ClassCode);
                    i.putExtra("User", UserID);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_bus){
                    Intent i = new Intent(teacherDashboard.this, TrackBus.class);
                    i.putExtra("ClassCode", ClassCode);
                    startActivity(i);
                }
                if(item.getItemId()==R.id.nav_logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(teacherDashboard.this, Login.class);
                    startActivity(i);
                    finish();
                }
                return true;
            }
        });
        Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(teacherDashboard.this, ParentsQueryTeacherSide.class);
                i.putExtra("ClassCode", ClassCode);
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
                startActivity(i);
            }
        });
        Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(teacherDashboard.this, TrackBus.class);
                i.putExtra("ClassCode", ClassCode);
                startActivity(i);
            }
        });
    }
}