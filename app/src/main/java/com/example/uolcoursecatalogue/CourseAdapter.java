package com.example.uolcoursecatalogue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }

    // Static inner ViewHolder: finds the three TextViews once
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode;
        TextView tvTitle;
        TextView tvCredits;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCredits = itemView.findViewById(R.id.tvCredits);
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.tvCode.setText(course.getCode());
        holder.tvTitle.setText(course.getTitle());
        holder.tvCredits.setText("Credit Hours: " + course.getCreditHours());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}