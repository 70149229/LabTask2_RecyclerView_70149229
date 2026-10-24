package com.example.uolcoursecatalogue;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    public static final String EXTRA_CODE = "extra_code";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_INSTRUCTOR = "extra_instructor";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_DESCRIPTION = "extra_description";
    public static final String EXTRA_CREDITS = "extra_credits";

    private RecyclerView rvCourses;
    private ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvCourses = findViewById(R.id.rvCourses);

        courses = new ArrayList<>();
        courses.add(new Course("CS02310", "Mobile Application Development",
                "Sir Ahmed Raza", "70149221@teacher.uol.edu.pk",
                "Building Android apps with Java, Android Studio, RecyclerView and Intents.", 3));
        courses.add(new Course("SE02100", "Software Quality Assurance",
                "Dr. Sana Malik", "70149222@teacher.uol.edu.pk",
                "Testing levels, test design techniques, QA processes and quality standards.", 3));
        courses.add(new Course("SE02200", "Software Project Management",
                "Sir Hamza Tariq", "70149223@teacher.uol.edu.pk",
                "Planning, estimation, scheduling, risk management and team coordination.", 3));
        courses.add(new Course("SE02300", "Software Requirements Engineering",
                "Dr. Ayesha Khan", "70149224@teacher.uol.edu.pk",
                "Eliciting, analysing, specifying and validating software requirements.", 3));
        courses.add(new Course("CS02200", "Database Systems",
                "Sir Bilal Hussain", "70149225@teacher.uol.edu.pk",
                "Relational model, SQL, normalization, indexing and transactions.", 3));
        courses.add(new Course("CS02400", "Operating Systems",
                "Dr. Usman Farooq", "70149226@teacher.uol.edu.pk",
                "Processes, threads, scheduling, memory management and file systems.", 3));
        courses.add(new Course("CS02500", "Computer Networks",
                "Ms. Hina Shahid", "70149227@teacher.uol.edu.pk",
                "Network layers, TCP/IP, routing, switching and internet protocols.", 3));
        courses.add(new Course("SE02400", "Web Engineering",
                "Sir Danish Iqbal", "70149228@teacher.uol.edu.pk",
                "Designing and building web applications with HTML, CSS, JavaScript and back-end basics.", 3));

        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(new CourseAdapter(courses, this));
    }

    @Override
    public void onItemClick(int position) {
        Course course = courses.get(position);

        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra(EXTRA_CODE, course.getCode());
        intent.putExtra(EXTRA_TITLE, course.getTitle());
        intent.putExtra(EXTRA_INSTRUCTOR, course.getInstructor());
        intent.putExtra(EXTRA_EMAIL, course.getEmail());
        intent.putExtra(EXTRA_DESCRIPTION, course.getDescription());
        intent.putExtra(EXTRA_CREDITS, course.getCreditHours());
        startActivity(intent);
    }
}