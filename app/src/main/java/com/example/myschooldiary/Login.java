package com.example.myschooldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private TextView signup;
    private Button btn;
    private EditText email, pass;
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        signup = findViewById(R.id.signup_intent);
        btn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        dataBase = new DataBase(Login.this);
        //------------------------------------------------>
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Pass = pass.getText().toString();
                if(!Email.equalsIgnoreCase("") && !Pass.equalsIgnoreCase("")){
//                    database.
                }else {
                    Toast.makeText(Login.this, "Some Fields are Empty", Toast.LENGTH_LONG).show();
                }
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