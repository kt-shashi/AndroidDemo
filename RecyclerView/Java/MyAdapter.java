package com.shashi.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DataModel> dataList;

    public MyAdapter(Context context, ArrayList<DataModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        DataModel data = dataList.get(position);
        String name = data.getName();
        String phone = data.getPhone();

        holder.nameTextView.setText(name);
        holder.phoneTextView.setText(phone);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView phoneTextView;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view_item_layout);
            phoneTextView = itemView.findViewById(R.id.phone_text_view_item_layout);
            deleteButton = itemView.findViewById(R.id.delete_button_item_layout);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

}
