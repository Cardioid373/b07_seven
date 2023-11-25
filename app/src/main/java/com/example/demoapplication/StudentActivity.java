package com.example.demoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Button btnComplaints = findViewById(R.id.studentComplaintsButton);
        Button btnLogout = findViewById(R.id.studentLogoutButton);

        Button btnPost = findViewById(R.id.studentPostButton);
        btnPost.setOnClickListener(view -> {
            startActivity(new Intent(StudentActivity.this, StudentComplaintsActivity.class));
            finish();
        });


        btnComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jump to complaints page
                startActivity(new Intent(StudentActivity.this, StudentComplaintsActivity.class));
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return to login page
                startActivity(new Intent(StudentActivity.this, MainActivity.class));
                finish();
                Toast.makeText(StudentActivity.this, "Logout successful!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}