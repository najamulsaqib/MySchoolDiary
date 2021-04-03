package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class Login extends AppCompatActivity {

    private TextView signup;
    private Button btn;
    private EditText email, pass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String UserID, Designation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        signup = findViewById(R.id.signup_intent);
        btn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        progressBar = findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();
        //------------------------------------------------>
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Pass = pass.getText().toString();
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
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            reference = FirebaseDatabase.getInstance().getReference("Users");
                            UserID = user.getUid();
                            reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Users userGet = snapshot.getValue(Users.class);
                                    if(userGet != null){
                                        Designation = userGet.Designation;
                                        if(Designation.equals("Teacher")){
                                            startActivity(new Intent(Login.this, Teacher.class));
                                            finish();
                                        }
                                        else if(Designation.equals("Student")){
                                            startActivity(new Intent(Login.this, Student.class));
                                            finish();
                                        }
//                                        progressBar.setVisibility(View.GONE);
                                    }else {
                                        Toast.makeText(Login.this, "Empty", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(Login.this, "Something Happened Wrong!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else {
                            Toast.makeText(Login.this, "Wrong Email or Password!", Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });
    }
}