package com.example.demoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WriteAnnouncementActivity extends AppCompatActivity {

    private EditText editAnnouncementTitle;
    private EditText editAnnouncementBody;
    private Button announcementPostBtn;
    private Button announcementBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_announcement);

        editAnnouncementTitle = findViewById(R.id.editAnnouncementTitle);
        editAnnouncementBody = findViewById(R.id.editAnnouncementBody);
        announcementPostBtn = findViewById((R.id.announcementPostBtn));
        announcementBackBtn = findViewById(R.id.announcementBackBtn);

        announcementPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAnnouncement();
            }
        });

        announcementBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return to student page
                startActivity(new Intent(WriteAnnouncementActivity.this, AdminHomeActivity.class));
                finish();
            }
        });
    }

    protected void createAnnouncement(){
        // Retrieve Title and Body
        String title = editAnnouncementTitle.getText().toString();
        String body = editAnnouncementBody.getText().toString();

        if (title.isEmpty() || body.isEmpty()) {
            Toast.makeText(WriteAnnouncementActivity.this, "Subject or body cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve Date and Time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        String currentDate;
        String currentTime;

        try {
            currentDate = dateFormat.format(calendar.getTime());
            currentTime = timeFormat.format(calendar.getTime());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Toast.makeText(WriteAnnouncementActivity.this, "Error formatting date or time", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve Author (admin username)
        String author = "Darren";

        //Instantiate announcement object and add to database
        Announcement announcement = new Announcement(title, currentDate, currentTime, body, author);
        DatabaseReference adminAnnouncementsRef = FirebaseDatabase.getInstance().getReference("adminAnnouncements");

        // set the value of our announcement object a unique key for the announcement
        String announcementKey = adminAnnouncementsRef.push().getKey();

        adminAnnouncementsRef.child(announcementKey).setValue(announcement)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(WriteAnnouncementActivity.this, "Announcement posted", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("WriteAnnouncement", "Error posting announcement", task.getException());
                        Toast.makeText(WriteAnnouncementActivity.this, "Failed to post announcement", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
