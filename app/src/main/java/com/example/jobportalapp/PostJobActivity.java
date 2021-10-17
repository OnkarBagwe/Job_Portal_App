package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobportalapp.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJobActivity extends AppCompatActivity {

    private FloatingActionButton fabBtn;

    //Recycler view...

    private RecyclerView recyclerView;

    //Firebase...

    private FirebaseAuth mAuth;
    private DatabaseReference JobPostDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        fabBtn=findViewById(R.id.fab_add);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        JobPostDatabase = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        recyclerView = findViewById(R.id.recycler_job_post_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data, MyViewHolder>adapter = new FirebaseRecyclerAdapter<Data, MyViewHolder>(
                Data.class,
                R.layout.job_post_item,
                MyViewHolder.class,
                JobPostDatabase
        ) {
            @Override
            protected void populateViewHolder (MyViewHolder viewHolder, Data model, int position){
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDate(model.getDate());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setJobSkills(model.getSkills());
                viewHolder.setJobSalary(model.getSalary());
            }

        };
        recyclerView.setAdapter(adapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View myview;
        public MyViewHolder(View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle= myview.findViewById(R.id.job_title);
            mTitle.setText(title);
        }
        public void setJobDate(String date) {
            TextView mDate = myview.findViewById(R.id.job_date);
            mDate.setText(date);
        }
        public void setJobDescription(String description) {
                TextView mDescription= myview.findViewById(R.id.job_description);
                mDescription.setText(description);
            }
        public void setJobSkills(String skills){
                TextView mskills= myview.findViewById(R.id.job_skills);
                mskills.setText(skills);
            }

        public void setJobSalary(String salary){
            TextView msalary= myview.findViewById(R.id.job_salary);
            msalary.setText(salary);
        }

        }
}
