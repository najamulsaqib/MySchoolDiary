package com.example.myschooldiary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>{
    ArrayList<model> dataList;
    Context context;
    FirebaseFirestore db;

    public myAdapter(ArrayList<model> dataList, Context context) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row_teacher, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.topic.setText(dataList.get(position).getTopic());
        holder.desc.setText(dataList.get(position).getDescription());
        db = FirebaseFirestore.getInstance();
        holder.topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_work))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = dialog.getHolderView();
                TextView topic = holderView.findViewById(R.id.topic_view);
                TextView description = holderView.findViewById(R.id.description_view);
                topic.setText(holder.topic.getText());
                description.setText(holder.desc.getText());
                dialog.show();
            }
        });
        holder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_work))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout) dialog.getHolderView();
                TextView topic = holderView.findViewById(R.id.topic_view);
                TextView description = holderView.findViewById(R.id.description_view);
                topic.setText(holder.topic.getText());
                description.setText(holder.desc.getText());
                dialog.show();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.content))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout) dialog.getHolderView();
                EditText topic = holderView.findViewById(R.id.topic);
                EditText description = holderView.findViewById(R.id.description);
                Button update_work = holderView.findViewById(R.id.update_work);
                topic.setText(holder.topic.getText());
                description.setText(holder.desc.getText());

                update_work.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String topic_updated = topic.getText().toString();
                        String description_updated = description.getText().toString();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("Topic", topic_updated);
                        map.put("Description", description_updated);
                        System.out.println(dataList.get(position).getId());

                        db.collection("Work")
                                .document(dataList.get(position).getId())
                                .update(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(context, "Data Updated!", Toast.LENGTH_LONG).show();
                                            holder.topic.setText(topic_updated);
                                            holder.desc.setText(description_updated);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogPlus dialogDelete = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.delete))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout) dialogDelete.getHolderView();
                Button btn_delete = holderView.findViewById(R.id.delete_btn);
                Button btn_cancel = holderView.findViewById(R.id.cancel_btn);
                TextView title = holderView.findViewById(R.id.title_delete);
                title.setText("Do you want to Delete '"+holder.topic.getText()+"'?");
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDelete.dismiss();
                    }
                });
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(dataList.get(position).getId());
                        db.collection("Work")
                                .document(dataList.get(position).getId())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(context, "Data Deleted!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                        dataList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dataList.size());
                        holder.itemView.setVisibility(View.GONE);
                        dialogDelete.dismiss();
                    }
                });
                dialogDelete.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView topic, desc;
        ImageView edit, delete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            desc = itemView.findViewById(R.id.description);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}