package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.jobportalapp.Model.ProfileData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passReg;
    private EditText full_name;
    private EditText phone_no;
    private EditText designation;

    private Button btnReg;
    private Button btnLogin;

    //Firebase authentication
    private FirebaseAuth mAuth;

    //Firebase
    private DatabaseReference Userdata;

    //Progress dialog..

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        Userdata = FirebaseDatabase.getInstance().getReference().child("User Data");

        mDialog = new ProgressDialog(this);

        Registration();
    }

    private void Registration(){

        emailReg = findViewById(R.id.email_registration);
        passReg = findViewById(R.id.registration_password);
        full_name = findViewById(R.id.full_name);
        phone_no = findViewById(R.id.phone_no);
        designation = findViewById(R.id.designation);

        btnReg = findViewById(R.id.btn_reg);
        btnLogin = findViewById(R.id.btn_login);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailReg.getText().toString().trim();
                String pass = passReg.getText().toString().trim();
                String fullname = full_name.getText().toString().trim();
                String phoneno = phone_no.getText().toString().trim();
                String desig = designation.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailReg.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    passReg.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(fullname)){
                    full_name.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(phoneno)){
                    phone_no.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(desig)){
                    designation.setError("Required Field...");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            String user_id = mAuth.getCurrentUser().getUid();
                            String join_date = DateFormat.getDateInstance().format(new Date());
                            ProfileData data = new ProfileData (fullname, phoneno, desig, email, pass, user_id, join_date);
                            Userdata.child(user_id).setValue(data);
                            Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            mDialog.dismiss();

                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        }));
    }
}
