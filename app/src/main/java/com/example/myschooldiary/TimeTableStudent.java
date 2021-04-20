package com.example.myschooldiary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class TimeTableStudent extends AppCompatActivity {

    private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_student);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        dataList = new ArrayList<>();
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);
        firebaseFirestore = FirebaseFirestore.getInstance();


        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Monday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Monday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTableStudent.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Tuesday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Tuesday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTableStudent.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Wednesday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Wednesday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTableStudent.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Thursday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Thursday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTableStudent.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Friday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Friday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTableStudent.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Saturday");
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_time_table))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                ListView listView = holderView.findViewById(R.id.listView);
                TextView textView = holderView.findViewById(R.id.day);
                textView.setText("Saturday");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TimeTableStudent.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
                dialog.show();
            }
        });
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(TimeTableStudent.this)
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