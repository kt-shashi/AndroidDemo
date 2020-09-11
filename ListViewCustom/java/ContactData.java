package com.shashi.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactData extends AppCompatActivity {

    ImageView imageView;
    TextView nameTextView;
    TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_data);

        initViews();

        String name = getIntent().getStringExtra("contact_name");
        String phone = getIntent().getStringExtra("contact_phone");
        int imageId = getIntent().getIntExtra("contact_image", 0);

        nameTextView.setText(name);
        phoneTextView.setText(phone);
        imageView.setImageDrawable(getApplicationContext().getResources().getDrawable(imageId));

    }

    private void initViews() {
        imageView = findViewById(R.id.contact_image_view_contact_data_activity);
        nameTextView = findViewById(R.id.contact_name_text_view_contactdata_activity);
        phoneTextView = findViewById(R.id.phone_text_view_contactdata_activity);
    }
}