package com.example.appbookroom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookroom.R;
import com.example.appbookroom.entity.Employee;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    private Context context;
    private List<Employee> employeeList;

    public PeopleAdapter(List<Employee> employeeArrayList, Context context) {
        this.employeeList = employeeArrayList;
        this.context = context;
    }



    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_recyclerview,parent,false);
        context = parent.getContext();
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PeopleAdapter.PeopleViewHolder holder, int position) {
            Employee employee = employeeList.get(position);
            holder.tvName.setText(employee.getName());
            holder.tvEmail.setText(employee.getEmail());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvEmail;
        public PeopleViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            initUI();
        }
        private void initUI(){
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

}
