package com.shashi.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MyListAdapter extends BaseAdapter {

    Context context;
    ArrayList<ContactDataModel> dataModelList;

    public MyListAdapter(Context context, ArrayList<ContactDataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @Override
    public int getCount() {
        return dataModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.contact_list_layout, null);

        ImageView imageView = rootView.findViewById(R.id.contact_image_view_contact_layout);
        TextView nameTextView = rootView.findViewById(R.id.contact_name_text_view_layout);
        TextView phoneTextView = rootView.findViewById(R.id.contact_phone_text_view_layout);

        ContactDataModel data = (ContactDataModel) getItem(i);

        imageView.setImageDrawable(context.getResources().getDrawable(data.getImageId()));
        nameTextView.setText(data.getContactName());
        phoneTextView.setText(data.getContactPhoneNumber());

        return rootView;
    }


}
