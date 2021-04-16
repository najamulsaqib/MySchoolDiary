package com.example.myschooldiary;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.myViewHolder> {
    ArrayList<model> dataList;
    Context context;
    FirebaseFirestore db;

    public customAdapter(ArrayList<model> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public customAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.myViewHolder holder, int position) {
        holder.topic.setText(dataList.get(position).getTopic());
        holder.desc.setText(dataList.get(position).getDescription());
        String Image = dataList.get(position).getImage();
        if(Image.equalsIgnoreCase("science")){
            holder.subject.setText("Science");
            holder.imageView.setImageResource(R.drawable.science);
        }else if(Image.equalsIgnoreCase("english")){
            holder.subject.setText("English");
            holder.imageView.setImageResource(R.drawable.english);
        }else if(Image.equalsIgnoreCase("urdu")){
            holder.subject.setText("Urdu");
            holder.imageView.setImageResource(R.drawable.urdu);
        }else if(Image.equalsIgnoreCase("math")){
            holder.subject.setText("Mathematics");
            holder.imageView.setImageResource(R.drawable.math);
        }else if(Image.equalsIgnoreCase("gk")){
            holder.subject.setText("General Knowledge");
            holder.imageView.setImageResource(R.drawable.gk);
        }else if(Image.equalsIgnoreCase("others")){
            holder.subject.setText("Others");
            holder.imageView.setImageResource(R.drawable.others);
        }
        holder.topic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.view_work))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View holderView = (LinearLayout) dialog.getHolderView();
                ImageView  img = holderView.findViewById(R.id.image_work);
                TextView topic = holderView.findViewById(R.id.topic_view);
                TextView description = holderView.findViewById(R.id.description_view);
                topic.setText(holder.topic.getText());
                description.setText(holder.desc.getText());
                if(Image.equalsIgnoreCase("science")){
                    img.setImageResource(R.drawable.science);
                }else if(Image.equalsIgnoreCase("english")){
                    img.setImageResource(R.drawable.english);
                }else if(Image.equalsIgnoreCase("urdu")){
                    img.setImageResource(R.drawable.urdu);
                }else if(Image.equalsIgnoreCase("math")){
                    img.setImageResource(R.drawable.math);
                }else if(Image.equalsIgnoreCase("gk")){
                    img.setImageResource(R.drawable.gk);
                }else if(Image.equalsIgnoreCase("others")){
                    img.setImageResource(R.drawable.others);
                }
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
                ImageView  img = holderView.findViewById(R.id.image_work);
                TextView topic = holderView.findViewById(R.id.topic_view);
                TextView description = holderView.findViewById(R.id.description_view);
                if(Image.equalsIgnoreCase("science")){
                    img.setImageResource(R.drawable.science);
                }else if(Image.equalsIgnoreCase("english")){
                    img.setImageResource(R.drawable.english);
                }else if(Image.equalsIgnoreCase("urdu")){
                    img.setImageResource(R.drawable.urdu);
                }else if(Image.equalsIgnoreCase("math")){
                    img.setImageResource(R.drawable.math);
                }else if(Image.equalsIgnoreCase("gk")){
                    img.setImageResource(R.drawable.gk);
                }else if(Image.equalsIgnoreCase("others")){
                    img.setImageResource(R.drawable.others);
                }
                topic.setText(holder.topic.getText());
                description.setText(holder.desc.getText());
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView topic, desc, subject;
        ImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.Topic);
            desc = itemView.findViewById(R.id.description);
            subject = itemView.findViewById(R.id.subject);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
