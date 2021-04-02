package com.example.myschooldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;
import java.util.UUID;

import javax.crypto.NullCipher;

public class Signup extends AppCompatActivity {

    private TextView login;
    private RadioGroup rg;
    private EditText class_code, name, email, pass;
    private Button btn;
    String Id, Name, Email, Pass, Code, designation;
    private DataBase dataBase;
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
        dataBase = new DataBase(Signup.this);
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
                    dataBase.SignUp(Name,Email,Pass,Code, designation);
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