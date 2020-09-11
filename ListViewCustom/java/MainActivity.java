package com.shashi.customlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ContactDataModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        initData();

        MyListAdapter myListAdapter = new MyListAdapter(this, dataList);
        listView = findViewById(R.id.contact_list_view);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactDataModel dataClicked = (ContactDataModel) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, ContactData.class);
                intent.putExtra("contact_name", dataClicked.getContactName());
                intent.putExtra("contact_phone", dataClicked.getContactPhoneNumber());
                intent.putExtra("contact_image", dataClicked.getImageId());
                startActivity(intent);
            }
        });

    }

    private void initData() {

        ContactDataModel data1 = new ContactDataModel();
        data1.setImageId(R.drawable.girl1);
        data1.setContactName("Name1");
        data1.setContactPhoneNumber("+91 1312654987");
        dataList.add(data1);

        ContactDataModel data2 = new ContactDataModel();
        data2.setImageId(R.drawable.man1);
        data2.setContactName("Name2");
        data2.setContactPhoneNumber("+91 2321654978");
        dataList.add(data2);

        ContactDataModel data3 = new ContactDataModel();
        data3.setImageId(R.drawable.man3);
        data3.setContactName("Name3");
        data3.setContactPhoneNumber("+91 3321654987");
        dataList.add(data3);

        ContactDataModel data4 = new ContactDataModel();
        data4.setImageId(R.drawable.girl1);
        data4.setContactName("Name4");
        data4.setContactPhoneNumber("+91 4321654987");
        dataList.add(data4);

        ContactDataModel data5 = new ContactDataModel();
        data5.setImageId(R.drawable.girl3);
        data5.setContactName("Name5");
        data5.setContactPhoneNumber("+91 5321654978");
        dataList.add(data5);

        ContactDataModel data6 = new ContactDataModel();
        data6.setImageId(R.drawable.man1);
        data6.setContactName("Name6");
        data6.setContactPhoneNumber("+91 6321645987");
        dataList.add(data6);

    }

}