package com.example.myschooldiary;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DataBase {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    DataBase(Context context){
        this.context = context;
    }

    public void SignUp(String Name, String Email, String Pass, String Code, String Designation){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Name", Name);
        map.put("Email", Email);
        map.put("Pass", Pass);
        map.put("ClassCode", Code);
        map.put("Designation", Designation);

        db.collection("User").document(Email).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "Data Saved!", Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "UnKnown Error!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
