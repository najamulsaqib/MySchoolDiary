package com.example.myschooldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    private TextView login;
    private RadioGroup rg;
    private EditText class_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        login = findViewById(R.id.login_intent);
        rg = findViewById(R.id.radio);
        class_code = findViewById(R.id.class_code);

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
                        break;
                    case R.id.r_teacher:
                        class_code.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
    }
}