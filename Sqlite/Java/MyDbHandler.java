package com.shashi.employeemanagementsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class MyDbHandler extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "employeetable";
    public static final String key_id = "id";
    public static final String key_name = "name";
    public static final String key_department = "department";
    public static final String key_salary = "salary";

    public MyDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    //to create DB
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" + key_id + " INTEGER PRIMARY KEY," +
                key_name + " TEXT," + key_department + " TEXT," + key_salary + " Text)";

        sqLiteDatabase.execSQL(createTableQuery);

    }

    //To upgrade db version infuture
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //CRUD operations:

    //Create an employee entry
    public void createEmployee(EmployeeDataModel employee) {
        String emp_id = employee.getId();
        String emp_name = employee.getName();
        String emp_department = employee.getDepartment();
        String emp_salary = employee.getSalary();

        ContentValues contentValues = new ContentValues();
        contentValues.put(key_id, emp_id);
        contentValues.put(key_name, emp_name);
        contentValues.put(key_department, emp_department);
        contentValues.put(key_salary, emp_salary);

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(TABLE_NAME,null, contentValues);
        database.close();
    }

    //Read data of Employees
    public ArrayList<EmployeeDataModel> readEmployeeData() {
        String select_query = "SELECT * FROM " + TABLE_NAME;
        ArrayList<EmployeeDataModel> employeeList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        //Return type of RawQuery is Cursor
        Cursor cursor = database.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {

                EmployeeDataModel employee = new EmployeeDataModel();
                employee.setId(cursor.getString(0));
                employee.setName(cursor.getString(1));
                employee.setDepartment(cursor.getString(2));
                employee.setSalary(cursor.getString(3));

                employeeList.add(employee);

            } while (cursor.moveToNext());
        }

        database.close();
        return employeeList;

    }

    //Update data of Employee
    public void updateEmployee(EmployeeDataModel employee) {

        String emp_id = employee.getId();
        String emp_name = employee.getName();
        String emp_department = employee.getDepartment();
        String emp_salary = employee.getSalary();

        ContentValues contentValues = new ContentValues();
        contentValues.put(key_id, emp_id);
        contentValues.put(key_name, emp_name);
        contentValues.put(key_department, emp_department);
        contentValues.put(key_salary, emp_salary);

        SQLiteDatabase database = this.getWritableDatabase();

        database.update(TABLE_NAME, contentValues, "id=?", new String[]{employee.getId()});
        database.close();

    }

    //Delete data of Employee
    public void deleteEmployee(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, key_id + "=?", new String[]{id});
        database.close();
    }
}
