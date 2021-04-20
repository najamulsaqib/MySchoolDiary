package com.example.myschooldiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeTable extends AppCompatActivity {

    private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private String ClassCode, UserID;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> dataList;
    private ImageView monday_btn, tuesday_btn, wednesday_btn, thursday_btn, friday_btn, saturday_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        dataList = new ArrayList<>();
        monday_btn = findViewById(R.id.monday_btn);
        tuesday_btn = findViewById(R.id.tuesday_btn);
        wednesday_btn = findViewById(R.id.wednesday_btn);
        thursday_btn = findViewById(R.id.thursday_btn);
        friday_btn = findViewById(R.id.friday_btn);
        saturday_btn = findViewById(R.id.saturday_btn);

        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);

        firebaseFirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        ClassCode = intent.getStringExtra("ClassCode");
        UserID = intent.getStringExtra("User");
        monday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Monday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.edit_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                Button updateTime_btn = holderView.findViewById(R.id.updateTime_btn);
                EditText lec1 = holderView.findViewById(R.id.lec1);
                EditText lec2 = holderView.findViewById(R.id.lec2);
                EditText lec3 = holderView.findViewById(R.id.lec3);
                EditText lec4 = holderView.findViewById(R.id.lec4);
                EditText lec5 = holderView.findViewById(R.id.lec5);
                EditText lec6 = holderView.findViewById(R.id.lec6);
                EditText lec7 = holderView.findViewById(R.id.lec7);
                if(dataList.size() < 1){
                    return;
                }
                textView.setText("Monday");
                lec1.setText(dataList.get(0));
                lec2.setText(dataList.get(1));
                lec3.setText(dataList.get(2));
                lec4.setText(dataList.get(3));
                lec5.setText(dataList.get(4));
                lec6.setText(dataList.get(5));
                lec7.setText(dataList.get(6));

                updateTime_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String one = lec1.getText().toString();
                        String two = lec2.getText().toString();
                        String three = lec3.getText().toString();
                        String four = lec4.getText().toString();
                        String five = lec5.getText().toString();
                        String six = lec6.getText().toString();
                        String seven = lec7.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Science", one);
                        map.put("Math", two);
                        map.put("Islamic Studies", three);
                        map.put("Social Studies", four);
                        map.put("Break", five);
                        map.put("Drawing", six);
                        map.put("General Knowledge", seven);

                        firebaseFirestore.collection("TimeTable")
                                .document("Monday")
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(TimeTable.this, "Time Table Updated!", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TimeTable.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        tuesday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Tuesday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.edit_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                Button updateTime_btn = holderView.findViewById(R.id.updateTime_btn);
                EditText lec1 = holderView.findViewById(R.id.lec1);
                EditText lec2 = holderView.findViewById(R.id.lec2);
                EditText lec3 = holderView.findViewById(R.id.lec3);
                EditText lec4 = holderView.findViewById(R.id.lec4);
                EditText lec5 = holderView.findViewById(R.id.lec5);
                EditText lec6 = holderView.findViewById(R.id.lec6);
                EditText lec7 = holderView.findViewById(R.id.lec7);
                if(dataList.size() < 1){
                    return;
                }
                textView.setText("Tuesday");
                lec1.setText(dataList.get(0));
                lec2.setText(dataList.get(1));
                lec3.setText(dataList.get(2));
                lec4.setText(dataList.get(3));
                lec5.setText(dataList.get(4));
                lec6.setText(dataList.get(5));
                lec7.setText(dataList.get(6));

                updateTime_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String one = lec1.getText().toString();
                        String two = lec2.getText().toString();
                        String three = lec3.getText().toString();
                        String four = lec4.getText().toString();
                        String five = lec5.getText().toString();
                        String six = lec6.getText().toString();
                        String seven = lec7.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Science", one);
                        map.put("Math", two);
                        map.put("Islamic Studies", three);
                        map.put("Social Studies", four);
                        map.put("Break", five);
                        map.put("Drawing", six);
                        map.put("General Knowledge", seven);

                        firebaseFirestore.collection("TimeTable")
                                .document("Tuesday")
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(TimeTable.this, "Time Table Updated!", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TimeTable.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        wednesday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Wednesday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.edit_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                Button updateTime_btn = holderView.findViewById(R.id.updateTime_btn);
                EditText lec1 = holderView.findViewById(R.id.lec1);
                EditText lec2 = holderView.findViewById(R.id.lec2);
                EditText lec3 = holderView.findViewById(R.id.lec3);
                EditText lec4 = holderView.findViewById(R.id.lec4);
                EditText lec5 = holderView.findViewById(R.id.lec5);
                EditText lec6 = holderView.findViewById(R.id.lec6);
                EditText lec7 = holderView.findViewById(R.id.lec7);
                if(dataList.size() < 1){
                    return;
                }
                textView.setText("Wednesday");
                lec1.setText(dataList.get(0));
                lec2.setText(dataList.get(1));
                lec3.setText(dataList.get(2));
                lec4.setText(dataList.get(3));
                lec5.setText(dataList.get(4));
                lec6.setText(dataList.get(5));
                lec7.setText(dataList.get(6));

                updateTime_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String one = lec1.getText().toString();
                        String two = lec2.getText().toString();
                        String three = lec3.getText().toString();
                        String four = lec4.getText().toString();
                        String five = lec5.getText().toString();
                        String six = lec6.getText().toString();
                        String seven = lec7.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Science", one);
                        map.put("Math", two);
                        map.put("Islamic Studies", three);
                        map.put("Social Studies", four);
                        map.put("Break", five);
                        map.put("Drawing", six);
                        map.put("General Knowledge", seven);

                        firebaseFirestore.collection("TimeTable")
                                .document("Wednesday")
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(TimeTable.this, "Time Table Updated!", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TimeTable.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        thursday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Thursday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.edit_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                Button updateTime_btn = holderView.findViewById(R.id.updateTime_btn);
                EditText lec1 = holderView.findViewById(R.id.lec1);
                EditText lec2 = holderView.findViewById(R.id.lec2);
                EditText lec3 = holderView.findViewById(R.id.lec3);
                EditText lec4 = holderView.findViewById(R.id.lec4);
                EditText lec5 = holderView.findViewById(R.id.lec5);
                EditText lec6 = holderView.findViewById(R.id.lec6);
                EditText lec7 = holderView.findViewById(R.id.lec7);
                if(dataList.size() < 1){
                    return;
                }
                textView.setText("Thursday");
                lec1.setText(dataList.get(0));
                lec2.setText(dataList.get(1));
                lec3.setText(dataList.get(2));
                lec4.setText(dataList.get(3));
                lec5.setText(dataList.get(4));
                lec6.setText(dataList.get(5));
                lec7.setText(dataList.get(6));

                updateTime_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String one = lec1.getText().toString();
                        String two = lec2.getText().toString();
                        String three = lec3.getText().toString();
                        String four = lec4.getText().toString();
                        String five = lec5.getText().toString();
                        String six = lec6.getText().toString();
                        String seven = lec7.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Science", one);
                        map.put("Math", two);
                        map.put("Islamic Studies", three);
                        map.put("Social Studies", four);
                        map.put("Break", five);
                        map.put("Drawing", six);
                        map.put("General Knowledge", seven);

                        firebaseFirestore.collection("TimeTable")
                                .document("Thursday")
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(TimeTable.this, "Time Table Updated!", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TimeTable.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        friday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Friday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.edit_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                Button updateTime_btn = holderView.findViewById(R.id.updateTime_btn);
                EditText lec1 = holderView.findViewById(R.id.lec1);
                EditText lec2 = holderView.findViewById(R.id.lec2);
                EditText lec3 = holderView.findViewById(R.id.lec3);
                EditText lec4 = holderView.findViewById(R.id.lec4);
                EditText lec5 = holderView.findViewById(R.id.lec5);
                EditText lec6 = holderView.findViewById(R.id.lec6);
                EditText lec7 = holderView.findViewById(R.id.lec7);
                if(dataList.size() < 1){
                    return;
                }
                textView.setText("Friday");
                lec1.setText(dataList.get(0));
                lec2.setText(dataList.get(1));
                lec3.setText(dataList.get(2));
                lec4.setText(dataList.get(3));
                lec5.setText(dataList.get(4));
                lec6.setText(dataList.get(5));
                lec7.setText(dataList.get(6));

                updateTime_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String one = lec1.getText().toString();
                        String two = lec2.getText().toString();
                        String three = lec3.getText().toString();
                        String four = lec4.getText().toString();
                        String five = lec5.getText().toString();
                        String six = lec6.getText().toString();
                        String seven = lec7.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Science", one);
                        map.put("Math", two);
                        map.put("Islamic Studies", three);
                        map.put("Social Studies", four);
                        map.put("Break", five);
                        map.put("Drawing", six);
                        map.put("General Knowledge", seven);

                        firebaseFirestore.collection("TimeTable")
                                .document("Friday")
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(TimeTable.this, "Time Table Updated!", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TimeTable.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        saturday_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Saturday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.edit_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                Button updateTime_btn = holderView.findViewById(R.id.updateTime_btn);
                EditText lec1 = holderView.findViewById(R.id.lec1);
                EditText lec2 = holderView.findViewById(R.id.lec2);
                EditText lec3 = holderView.findViewById(R.id.lec3);
                EditText lec4 = holderView.findViewById(R.id.lec4);
                EditText lec5 = holderView.findViewById(R.id.lec5);
                EditText lec6 = holderView.findViewById(R.id.lec6);
                EditText lec7 = holderView.findViewById(R.id.lec7);
                if(dataList.size() < 1){
                    return;
                }
                textView.setText("Saturday");
                lec1.setText(dataList.get(0));
                lec2.setText(dataList.get(1));
                lec3.setText(dataList.get(2));
                lec4.setText(dataList.get(3));
                lec5.setText(dataList.get(4));
                lec6.setText(dataList.get(5));
                lec7.setText(dataList.get(6));

                updateTime_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String one = lec1.getText().toString();
                        String two = lec2.getText().toString();
                        String three = lec3.getText().toString();
                        String four = lec4.getText().toString();
                        String five = lec5.getText().toString();
                        String six = lec6.getText().toString();
                        String seven = lec7.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Science", one);
                        map.put("Math", two);
                        map.put("Islamic Studies", three);
                        map.put("Social Studies", four);
                        map.put("Break", five);
                        map.put("Drawing", six);
                        map.put("General Knowledge", seven);

                        firebaseFirestore.collection("TimeTable")
                                .document("Saturday")
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(TimeTable.this, "Time Table Updated!", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TimeTable.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Monday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Monday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTable.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Tuesday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Tuesday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTable.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Wednesday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Wednesday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTable.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Thursday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Thursday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTable.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Friday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Friday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTable.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Saturday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Saturday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTable.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(TimeTable.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView textView = holderView.findViewById(R.id.day);
                ImageView imageView = holderView.findViewById(R.id.nothing);
                ListView listView = holderView.findViewById(R.id.listView);
                listView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                dialog.show();
            }
        });
    }
    public void getData(String Day){
        DocumentReference documentReference = firebaseFirestore.collection("TimeTable").document(Day);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                dataList.clear();
                dataList.add(documentSnapshot.getString("Science"));
                dataList.add(documentSnapshot.getString("Math"));
                dataList.add(documentSnapshot.getString("Islamic Studies"));
                dataList.add(documentSnapshot.getString("Social Studies"));
                dataList.add(documentSnapshot.getString("Break"));
                dataList.add(documentSnapshot.getString("Drawing"));
                dataList.add(documentSnapshot.getString("General Knowledge"));
            }
        });
    }
}