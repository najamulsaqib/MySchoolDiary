package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;


import java.util.Random;

public class Signup extends AppCompatActivity {

    private TextView login;
    private RadioGroup rg;
    private EditText class_code, name, email, pass;
    private Button btn;
    private String Name, Email, Pass, Code, designation;
    private ProgressBar progressBar;
    private Users user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        //---------------------------------------------------->
        login = findViewById(R.id.login_intent);
        rg = findViewById(R.id.radio);
        class_code = findViewById(R.id.class_code);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btn = findViewById(R.id.signup_btn);
        progressBar = findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();
        designation = "Student";
        //----------------------------------------------------->
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.r_student:
                        class_code.setVisibility(View.VISIBLE);
                        designation = "Student";
                    break;
                    case R.id.r_teacher:
                        class_code.setVisibility(View.INVISIBLE);
                        designation = "Teacher";
                        break;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNetworkAvailable()){
                    Toast.makeText(Signup.this, "Internet Not Connected",Toast.LENGTH_LONG).show();
                    return;
                }
                Name = name.getText().toString().trim();
                Email = email.getText().toString().trim();
                Pass = pass.getText().toString();
                Code = class_code.getText().toString().toUpperCase();

                if(Name.isEmpty()){
                    name.setError("This field Can't be empty!");
                    name.requestFocus();
                    return;
                }
                if(Email.isEmpty()){
                    email.setError("This field Can't be empty!");
                    email.requestFocus();
                    return;
                }
                if(Pass.isEmpty()){
                    pass.setError("This field Can't be empty!");
                    pass.requestFocus();
                    return;
                }
                if(Pass.length() < 6){
                    pass.setError("Password length cannot be less than 6");
                    pass.requestFocus();
                    return;
                }
                if (Code.equalsIgnoreCase("") && designation.equals("Teacher")) {
                    Code = createClassCode();
                }
                if(Code.isEmpty()){
                    class_code.setError("This field Can't be empty!");
                    class_code.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(Email, Pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    user = new Users(Name, Email, Code, designation);
                                    String Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(Id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Signup.this, "SignUp Successful!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(Signup.this, Login.class));
//                                            progressBar.setVisibility(View.GONE);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, e.toString(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Signup.this, Signup.class));
                        finish();
                    }
                });
            }
        });
    }
//    ------------------------------------------------------------------------->
    public String createClassCode(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}