package com.shashi.employeemanagementsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {

    private List<EmployeeDataModel> employeList;
    private Context mContext;

    public ReadAdapter(List<EmployeeDataModel> employeList, Context mContext) {
        this.employeList = employeList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_data_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EmployeeDataModel employee = employeList.get(position);

        holder.idTextView.setText("ID: " + employee.getId());
        holder.nameTextView.setText("Name: " + employee.getName());
        holder.departmentTextView.setText("Department: " + employee.getDepartment());
        holder.salaryTextView.setText("Salary: " + employee.getSalary());

    }

    @Override
    public int getItemCount() {
        return employeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idTextView, nameTextView, departmentTextView, salaryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.id_text_view_read);
            nameTextView = itemView.findViewById(R.id.name_text_view_read);
            departmentTextView = itemView.findViewById(R.id.department_text_view_read);
            salaryTextView = itemView.findViewById(R.id.salary_text_view_read);

        }
    }

}
