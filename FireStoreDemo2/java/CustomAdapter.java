package com.shashi.cloudfirestor3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<ToDoDataModel> dataList;

    public CustomAdapter(Context context, ArrayList<ToDoDataModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_row_view_data, parent, false);

        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        ToDoDataModel data = dataList.get(position);

        String title = data.getTitle();
        String desc = data.getDescription();

        holder.titleTextView.setText(title);
        holder.descriptionTextView.setText(desc);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        TextView descriptionTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_text_view_list_layout);
            descriptionTextView = itemView.findViewById(R.id.description_text_view_list_layout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            ToDoDataModel dataModel = dataList.get(getAdapterPosition());

            Intent intent = new Intent(context, UpdateListActivity.class);
            intent.putExtra("todoitem", dataModel);

            context.startActivity(intent);

        }
    }
}
