package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button btnAlljob;
    private Button btnPostjob;
    private Button btnViewProfile;

    //Toolbar

    private Toolbar toolbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//
//        toolbar=findViewById(R.id.toolbar_home);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Job Portal App");

        mAuth=FirebaseAuth.getInstance();

        btnAlljob = findViewById(R.id.btn_allJob);
        btnPostjob = findViewById(R.id.btn_postJob);
        btnViewProfile = findViewById(R.id.btn_myprofile);

        btnAlljob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllJobActivity.class));
            }
        });

        btnPostjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InsertJobPostActivity.class));

            }
        });
        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mainmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
