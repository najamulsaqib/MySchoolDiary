package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import javax.crypto.NullCipher;

public class Signup extends AppCompatActivity {

    private TextView login;
    private RadioGroup rg;
    private EditText class_code, name, email, pass;
    private Button btn;
    String Name, Email, Pass, Code, designation;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
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
                Name = name.getText().toString();
                Email = email.getText().toString();
                Pass = pass.getText().toString();
                Code = class_code.getText().toString();
                if (Code.equalsIgnoreCase("") && designation.equals("Teacher")) {
                    Code = createClassCode();
                }
                if(!Name.equalsIgnoreCase("") && !Email.equalsIgnoreCase("") && !Pass.equalsIgnoreCase("")){
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        String Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("Id", Id);
                                        map.put("Email", Email);
                                        map.put("Name", Name);
                                        map.put("Pass", Pass);
                                        map.put("ClassCode", Code);

                                        db.collection("Users").document(Id).set(map)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(Signup.this, "SignUp Successful!", Toast.LENGTH_LONG).show();
                                                            startActivity(new Intent(Signup.this, Login.class));
                                                            finish();
                                                        }
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Signup.this, "UnKnown Error!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(Signup.this, Login.class));
                                                finish();
                                            }
                                        });
                                    }else {
                                        Toast.makeText(Signup.this, "UnKnown Error!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Signup.this, Login.class));
                                        finish();
                                    }
                                }
                            });
                    startActivity(new Intent(Signup.this, Login.class));
                    finish();
                }
                else {
                    Toast.makeText(Signup.this, "Some Input Fields are Empty", Toast.LENGTH_LONG).show();
                }
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
}