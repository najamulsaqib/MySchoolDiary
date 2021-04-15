package com.example.myschooldiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class popup extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        TextView day = findViewById(R.id.day);
        ListView listView = findViewById(R.id.lv1);
        TextView holiday = findViewById(R.id.holiday);
//        --->
        Intent i = getIntent();
        String Day = i.getStringExtra("Day");
        day.setText(Day);
        if(Day.equalsIgnoreCase("Saturday") || Day.equalsIgnoreCase("Sunday")) {
            listView.setVisibility(View.GONE);
            holiday.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Science 8:00AM to 8:45AM");
            arrayList.add("Math 8:45AM to 9:15AM");
            arrayList.add("Islamic Studies 9:15AM to 10:00AM");
            arrayList.add("Social Studies  10:00AM to 10:45AM");
            arrayList.add("Break 10:45AM to 11:15AM");
            arrayList.add("Drawing 11:15AM to 12:00AM");
            arrayList.add("General Knowledge 12:00AM to 01:45PM");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(popup.this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
        }
//        --->
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));
    }
}
