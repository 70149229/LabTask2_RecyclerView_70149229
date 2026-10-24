package com.example.uolcoursecatalogue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    // Interface that tells MainActivity which card was tapped
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ArrayList<Course> courses;
    private OnItemClickListener listener;

    public CourseAdapter(ArrayList<Course> courses, OnItemClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }

    // Static inner ViewHolder: finds the three TextViews once
    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode;
        TextView tvTitle;
        TextView tvCredits;

        public CourseViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCredits = itemView.findViewById(R.id.tvCredits);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view, listener);
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