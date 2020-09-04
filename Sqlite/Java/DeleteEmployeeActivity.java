package com.shashi.employeemanagementsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    MyDbHandler myDbHandler;

    EditText idEditText;
    Button deleteButton;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);

        initViews();

        myDbHandler = TempDataBaseHandler.getMyDbHandler();
    }

    public void initViews() {
        idEditText = findViewById(R.id.employee_id_edit_text_delete);
        deleteButton = findViewById(R.id.delete_button_delete);
        goBack = findViewById(R.id.go_back_delete);

        deleteButton.setOnClickListener(this);
        goBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_button_delete:
                deleteEmployee();
                break;
            case R.id.go_back_delete:
                goToMainActivity();
                break;
        }
    }

    private void deleteEmployee() {
        String emp_id = idEditText.getText().toString().trim();

        if (emp_id.isEmpty()) {
            idEditText.setError("Field cannot be empty");
            return;
        }

        //delete
        myDbHandler.deleteEmployee(emp_id);

        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
        idEditText.setText("");
    }

    private void goToMainActivity() {
        finish();
    }
}