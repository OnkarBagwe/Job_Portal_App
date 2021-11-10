package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobportalapp.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InsertJobPostActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private float dateValue;
    private EditText job_title;
    private EditText job_description;
    private EditText job_skills;
    private EditText job_salary;
    private EditText job_deadline;

    private Button btn_post_job;

    //Firebase...

    private FirebaseAuth mAuth;
    private DatabaseReference mJobPost;

    private DatabaseReference mPublicDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);

        toolbar = findViewById(R.id.insert_job_toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Job");

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        mPublicDatabase = FirebaseDatabase.getInstance().getReference().child("Public database");

        InsertJob();

    }

    private void InsertJob(){

        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_skills = findViewById(R.id.job_skill);
        job_salary = findViewById(R.id.job_salary);
        job_deadline = findViewById(R.id.job_deadline);

        btn_post_job = findViewById(R.id.btn_job_post);

        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = job_title.getText().toString().trim();
                String description = job_description.getText().toString().trim();
                String skills = job_skills.getText().toString().trim();
                String salary = job_salary.getText().toString().trim();
                String deadline = job_deadline.getText().toString().trim();

                try{
                    DateFormat format1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                    Date date1 = format1.parse(deadline);
                    long date1time = date1.getTime();
                    float days = (date1time / (1000*60*60*24));
                    dateValue = days*-1;

                } catch (ParseException e){
                    e.printStackTrace();
                }

                if(TextUtils.isEmpty(title)){
                    job_title.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(description)){
                    job_description.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(skills)){
                    job_skills.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(salary)){
                    job_salary.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(deadline)){
                    job_salary.setError("Required Field...");
                    return;
                }

                String id = mJobPost.push().getKey();
                String posted = DateFormat.getDateInstance().format(new Date());

                Data data = new Data (title, description, skills, salary, id, posted, deadline, dateValue);
                mJobPost.child(id).setValue(data);

                mPublicDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));

            }
        });

    }

}
