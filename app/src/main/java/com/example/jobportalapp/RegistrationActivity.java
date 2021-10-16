package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passReg;

    private Button btnReg;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Registration();
    }

    private void Registration(){

        emailReg = findViewById(R.id.email_registration);
        passReg = findViewById(R.id.registration_password);

        btnReg = findViewById(R.id.btn_reg);
        btnLogin = findViewById(R.id.btn_login);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailReg.getText().toString().trim();
                String pass = passReg.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailReg.setError("Required Field...");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    passReg.setError("Required Field...");
                    return;
                }

            }
        });

        btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));
    }
}
