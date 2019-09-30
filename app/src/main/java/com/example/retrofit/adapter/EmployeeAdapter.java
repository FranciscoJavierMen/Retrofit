package com.example.retrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.model.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private ArrayList<Employee> list;

    public EmployeeAdapter(ArrayList<Employee> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.phone.setText(list.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtEmployeeName);
            email = itemView.findViewById(R.id.txtEmployeeEmail);
            phone = itemView.findViewById(R.id.txtEmployeePhone);
        }
    }
}
