package com.example.myschooldiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class parentContactAdapter extends RecyclerView.Adapter<parentContactAdapter.viewHolder>  {
    ArrayList<QueryModel> dataList;
    Context context;
    FirebaseFirestore db;
    public parentContactAdapter(ArrayList<QueryModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parents_query_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.subject.setText(dataList.get(position).getSubject());
        holder.query.setText(dataList.get(position).getQuery());
        holder.name.setText(dataList.get(position).getName());
        db = FirebaseFirestore.getInstance();
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("teacher", true);
                db.collection("Note")
                        .document(dataList.get(position).getId())
                        .update(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "Done", Toast.LENGTH_LONG).show();
                                    dataList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, dataList.size());
                                    holder.itemView.setVisibility(View.GONE);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView subject, query, name;
        ImageView done;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            query = itemView.findViewById(R.id.query);
            name = itemView.findViewById(R.id.name);
            done = itemView.findViewById(R.id.done);
        }
    }
}
