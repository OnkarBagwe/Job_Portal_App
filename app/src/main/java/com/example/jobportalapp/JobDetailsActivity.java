package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class JobDetailsActivity extends AppCompatActivity {


    private Toolbar toolbar;

    private TextView mTitle;
    private TextView mDate;
    private TextView mDeadline;
    private TextView mDescription;
    private TextView mSkills;
    private TextView mSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        toolbar=findViewById(R.id.toolbar_job_details);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Job Details");

        mTitle=findViewById(R.id.job_details_title);
        mDate=findViewById(R.id.job_details_date);
        mDeadline=findViewById(R.id.job_details_deadline);
        mDescription=findViewById(R.id.job_details_description);
        mSkills=findViewById(R.id.job_details_skills);
        mSalary=findViewById(R.id.job_details_salary);

        //Receive data from all jo activity using intent..

        Intent intent = getIntent();

        String title=intent.getStringExtra("title");
        String date=intent.getStringExtra("date");
        String deadline=intent.getStringExtra("deadline");
        String description=intent.getStringExtra("description");
        String skills=intent.getStringExtra("skills");
        String salary=intent.getStringExtra("salary");

        mTitle.setText(title);
        mDate.setText(date);
        mDeadline.setText(deadline);
        mDescription.setText(description);
        mSkills.setText(skills);
        mSalary.setText(salary);
    }
}
