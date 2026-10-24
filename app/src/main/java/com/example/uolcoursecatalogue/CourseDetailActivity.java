package com.example.uolcoursecatalogue;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvCode = findViewById(R.id.tvDetailCode);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvInstructor = findViewById(R.id.tvDetailInstructor);
        TextView tvCredits = findViewById(R.id.tvDetailCredits);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);
        Button btnEmail = findViewById(R.id.btnEmailInstructor);

        // Read the values sent by MainActivity
        Intent intent = getIntent();
        String code = intent.getStringExtra(MainActivity.EXTRA_CODE);
        String title = intent.getStringExtra(MainActivity.EXTRA_TITLE);
        String instructor = intent.getStringExtra(MainActivity.EXTRA_INSTRUCTOR);
        String email = intent.getStringExtra(MainActivity.EXTRA_EMAIL);
        String description = intent.getStringExtra(MainActivity.EXTRA_DESCRIPTION);
        int credits = intent.getIntExtra(MainActivity.EXTRA_CREDITS, 0);

        // Screen title = course code
        setTitle(code);

        tvCode.setText(code);
        tvTitle.setText(title);
        tvInstructor.setText("Instructor: " + instructor);
        tvCredits.setText("Credit Hours: " + credits);
        tvDescription.setText(description);

        // Email Instructor button
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + email));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(CourseDetailActivity.this,
                            "No email app found on this device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}