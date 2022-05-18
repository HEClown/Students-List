package com.example.studentslist.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentslist.R;
import com.example.studentslist.model.StudentModel;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    List<StudentModel> students;
    StudentClickListener listener;

    public StudentAdapter(Context context, List<StudentModel> students, StudentClickListener listener) {
        this.context = context;
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View studentItem = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(studentItem);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.firstName.setText(students.get(position).getFirstName());
        holder.secondName.setText(students.get(position).getSecondName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView firstName;
        TextView secondName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_first_name);
            secondName = itemView.findViewById(R.id.tv_second_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }

    }

    public interface StudentClickListener {
        void onClick(View view, int position);
    }

}