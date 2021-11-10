package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jobportalapp.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class AllJobActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Query query1;
    //Recycler
    private RecyclerView recyclerView;

    //Firebase

    private DatabaseReference mAllJobPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionBarTheme);
        setContentView(R.layout.activity_all_job);

        toolbar=findViewById(R.id.all_job_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Job Post");

        //Database
        query1 = FirebaseDatabase.getInstance().getReference().child("Public database").orderByChild("dateValue");

        mAllJobPost = FirebaseDatabase.getInstance().getReference().child("Public database");
        mAllJobPost.keepSynced(true);

        recyclerView=findViewById(R.id.recycler_all_job);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart(){
        super.onStart();



        FirebaseRecyclerAdapter<Data, AllJobPostViewHolder> adapter=new FirebaseRecyclerAdapter<Data, AllJobPostViewHolder>
                (
                       Data.class,
                       R.layout.alljobpost,
                       AllJobPostViewHolder.class,
                        query1

                ) {
            @Override
            protected void populateViewHolder(AllJobPostViewHolder viewHolder, Data model, int i) {

                viewHolder.setJobTitle(model.getTitle());
//                viewHolder.setJobDate(model.getDate());
                viewHolder.setDeadlineDate(model.getDeadline());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setJobSkills(model.getSkills());
                viewHolder.setJobSalary(model.getSalary());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(),JobDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
//                        intent.putExtra("date",model.getDate());
                        intent.putExtra("deadline",model.getDeadline());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("skills",model.getSkills());
                        intent.putExtra("salary",model.getSalary());

                        startActivity(intent);

                    }
                });

            }

        };

        recyclerView.setAdapter(adapter);
    }

    public static class AllJobPostViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public AllJobPostViewHolder(View itemView){
            super(itemView);
            myview=itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle=myview.findViewById(R.id.all_job_post_title);
            mTitle.setText("Job Title: "+title);
        }

//        public void setJobDate(String date){
//            TextView mDate=myview.findViewById(R.id.all_job_post_date);
//            mDate.setText("Posted on: " +date);
//        }

        public void setDeadlineDate(String deadline){
            TextView mDeadline=myview.findViewById(R.id.all_job_post_deadline);
            mDeadline.setText("Deadline: " +deadline);
        }

        public void setJobDescription(String description){
            TextView mDescription=myview.findViewById(R.id.all_job_post_description);
            mDescription.setText("Job Description: "+description);
        }

        public void setJobSkills(String skills){
            TextView mSkills=myview.findViewById(R.id.all_job_post_skills);
            mSkills.setText("Skills Required: "+skills);
        }

        public void setJobSalary(String salary){
            TextView mSalary=myview.findViewById(R.id.all_job_post_salary);
            mSalary.setText("Salary Offered: "+salary);
        }

    }
}
