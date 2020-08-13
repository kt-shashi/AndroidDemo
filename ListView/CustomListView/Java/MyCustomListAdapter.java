package com.shashi.listviewdemo2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

class MyCustomListAdapter extends ArrayAdapter<ModelClassForData> {

    Context mContext;
    int resource;
    List<ModelClassForData> fruitList;
    int deletePosition;

    public MyCustomListAdapter(@NonNull Context context, int resource, List<ModelClassForData> fruitList) {
        super(context, resource, fruitList);

        this.mContext = context;
        this.resource = resource;
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(resource, null);

        ImageView imageView = view.findViewById(R.id.imageViewFruit);
        TextView nameTextView = view.findViewById(R.id.textViewName);
        TextView tasteTextView = view.findViewById(R.id.textViewTaste);

        //Binding the data
        ModelClassForData modelData = fruitList.get(position);
        imageView.setImageDrawable(mContext.getResources().getDrawable(modelData.getImage()));
        nameTextView.setText(modelData.getFruitName());
        tasteTextView.setText(modelData.getFruitTaste());

        final Button deleteButton = view.findViewById(R.id.buttonDelete);
        deletePosition = position;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFruitData(position);
            }
        });

        return view;
    }

    private void removeFruitData(int position) {
        fruitList.remove(position);
        notifyDataSetChanged();
    }
}
