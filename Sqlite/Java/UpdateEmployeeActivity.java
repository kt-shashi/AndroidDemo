package com.shashi.employeemanagementsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    MyDbHandler myDbHandler;

    EditText idEditText;
    EditText nameEditText;
    Spinner departmentSpinner;
    EditText salaryEditText;

    Button create;
    Button clear;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        myDbHandler = TempDataBaseHandler.getMyDbHandler();
        initViews();
    }

    private void initViews() {

        idEditText = findViewById(R.id.employee_id_edit_text_update);
        nameEditText = findViewById(R.id.employee_name_edit_text_update);
        departmentSpinner = findViewById(R.id.department_spinner_update);
        salaryEditText = findViewById(R.id.employee_salary_edit_text_update);

        create = findViewById(R.id.create_button_update);
        clear = findViewById(R.id.clear_button_update);
        goBack = findViewById(R.id.go_back_button_update);

        //set onClick Listners
        create.setOnClickListener(this);
        clear.setOnClickListener(this);
        goBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_button_update:
                updateEmployee();
                break;
            case R.id.clear_button_update:
                clearViews();
                break;
            case R.id.go_back_button_update:
                goBackActivity();
                break;
        }
    }

    private void updateEmployee() {

        //Get the data from UI
        String emp_id = idEditText.getText().toString().trim();
        String emp_name = nameEditText.getText().toString().trim();
        String emp_department = departmentSpinner.getSelectedItem().toString().trim();
        String emp_salary = salaryEditText.getText().toString().trim();

        //Check if the fields are filled
        if (!checkError(emp_id, emp_name, emp_department, emp_salary)) {
            return;
        }

        //Store the data in Model class
        EmployeeDataModel employee = new EmployeeDataModel();
        employee.setId(emp_id);
        employee.setName(emp_name);
        employee.setDepartment(emp_department);
        employee.setSalary(emp_salary);

        //Update an Entry
        myDbHandler.updateEmployee(employee);

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

    }

    private boolean checkError(String emp_id, String emp_name, String emp_department, String emp_salary) {

        if (emp_id.isEmpty()) {
            idEditText.setError("This field cannot be blank");
            return false;
        }

        if (emp_name.isEmpty()) {
            nameEditText.setError("This field cannot be blank");
            return false;
        }

        if (emp_department.equals("Choose Department")) {
            Toast.makeText(this, "Department cannot be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (emp_salary.isEmpty()) {
            salaryEditText.setError("This field cannot be blank");
            return false;
        }

        return true;

    }


    private void clearViews() {

        idEditText.setText("");
        nameEditText.setText("");
        salaryEditText.setText("");

    }

    private void goBackActivity() {
        finish();
    }
}