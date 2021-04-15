package com.example.myschooldiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class parentContactAdapter extends RecyclerView.Adapter<parentContactAdapter.viewHolder>  {
    ArrayList<QueryModel> dataList;
    Context context;

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
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView subject, query, name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            query = itemView.findViewById(R.id.query);
            name = itemView.findViewById(R.id.name);
        }
    }
}
