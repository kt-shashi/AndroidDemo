package com.shashi.menudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Go to res/menu (If not then create new Android resource directory)
        //Create new menu resource file: my_menu

        //In java:
        //override: onCreateoptionsMenu() to show the menu

        //For event handling
        //override: onOptionsItemSelected()

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.my_item_1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_item_2:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_item_3:
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_item_4:
                Toast.makeText(this, "Item 4 selected", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}