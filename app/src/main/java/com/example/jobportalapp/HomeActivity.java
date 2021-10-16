package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnAlljob;
    private Button btnPostjob;

    //Toolbar

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//
//        toolbar=findViewById(R.id.toolbar_home);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Job Portal App");
        btnAlljob = findViewById(R.id.btn_allJob);
        btnPostjob = findViewById(R.id.btn_postJob);

        btnAlljob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllJobActivity.class));
            }
        });

        btnPostjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PostJobActivity.class));

            }
        });
    }
}
