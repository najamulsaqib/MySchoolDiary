package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static int Splash_Time = 1000;
    private String UserID, Designation, ClassCode;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //------------------------------------------------>
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(user != null){
                    UserID = user.getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users userGet = snapshot.getValue(Users.class);
                            if(userGet != null){
                                Designation = userGet.Designation;
                                ClassCode = userGet.Code;
                                if(Designation.equals("Teacher")){
                                    Intent i = new Intent(MainActivity.this, teacherDashboard.class);
                                    i.putExtra("ClassCode", ClassCode);
                                    i.putExtra("User", UserID);
                                    startActivity(i);
                                    finish();
                                }
                                else if(Designation.equals("Student")){
                                    Intent i = new Intent(MainActivity.this, Student.class);
                                    i.putExtra("ClassCode", ClassCode);
                                    i.putExtra("User", UserID);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        }, Splash_Time);
    }
}